package cn.cerc.example.common;

import cn.cerc.core.IHandle;
import cn.cerc.mis.core.Application;

import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.IAppMenu;
import cn.cerc.mis.core.IPassport;
import cn.cerc.mis.core.MenuData;
import cn.cerc.mis.core.MenuItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MenuFactory implements IAppMenu {

    private static final Map<String, MenuData> menus = new LinkedHashMap<>();

    static {
        try {
            menus.clear();
            ApplicationContext forms = Application.getContext();
            for (String formCode : forms.getBeanDefinitionNames()) {
                Object bean = null;
                try {
                    bean = forms.getBean(formCode);
                } catch (BeanCreationException e) {
                    log.error(e.getMessage());
                    continue;
                } catch (CannotLoadBeanClassException e) {
                    log.error(String.format("bean %s not create!", formCode));
                    continue;
                }
                if (!(bean instanceof AbstractForm)) {
                    log.warn(String.format("%s not instanceof AbstractForm", formCode));
                    continue;
                }
                AbstractForm form = (AbstractForm) bean;

                MenuData menuItem = new MenuData();
                menuItem.setId(formCode);
                menuItem.setCaption(form.getCaption());
                menuItem.setProccode(form.getPermission());
                menuItem.setParent(form.getParent());
                menuItem.setSecurity("true".equals(form.getParam("security", "true")));
                menuItem.setHide("true".equals(form.getParam("hide", "false")));
                menuItem.setFolder("true".equals(form.getParam("folder", "false")));
                menuItem.setCustom("true".equals(form.getParam("custom", "false")));
                menuItem.setPhone(false);
                menuItem.setDescribe(form.getParam("remark", ""));
                menuItem.setWin(true);
                menuItem.setImage("");
                menuItem.setProcess(form.getParam("process", "release"));
                menuItem.setWeb(!"none".equals(menuItem.getProcess()));
                menuItem.setWin(!"release".equals(menuItem.getProcess()));
                menuItem.setVersions(form.getParam("verlist", ""));
                menus.put(menuItem.getId(), menuItem);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private IPassport passport;

    public static Map<String, MenuData> getItems() {
        return menus;
    }

    public static MenuData get(String beanID) {
        return menus.get(beanID);
    }

    @Override
    public MenuItem getItem(String menuId) {
        MenuData data = get(menuId);
        if (data == null)
            throw new RuntimeException(String.format("menu %s not find!", menuId));

        MenuItem menu = new MenuItem();
        menu.setId(menuId);
        setMenuInfo(menu, data);
        return menu;
    }

    public List<MenuItem> getList(IHandle handle, String parentMenu, boolean security) {
        if (this.passport == null) {
            this.passport = Application.getPassport(handle);
        }

        Map<String, MenuData> menus = MenuFactory.getItems();
        List<MenuItem> result = new ArrayList<MenuItem>();
        // 初筛出符合要求的菜单项
        for (String menuId : menus.keySet()) {
            MenuData data = menus.get(menuId);
            if (passItem(data, parentMenu, security)) {
                MenuItem menu = new MenuItem();
                menu.setId(menuId);
                setMenuInfo(menu, data);
                result.add(menu);
            }
        }
        return result;
    }

    protected boolean passItem(MenuData item, String parentId, boolean security) {
        // 不返回首页
        if (item.getId().equals(Application.getAppConfig().getFormDefault())) {
            return false;
        }

        // 不返回隐藏菜单
        if (item.getHide()) {
            return false;
        }

        if (!parentId.equals(item.getParent())) {
            return false;
        }

        // 当前用户是否拥有权限
        if (item.isSecurity() != security) {
            return false;
        }

        // 进行权限检查
        if (!passport.passProc(item.getVersions(), item.getProccode())) {
            return false;
        }

        return true;
    }

    private void setMenuInfo(MenuItem menu, MenuData data) {
        menu.setParam(MenuItem.TITLE, data.getCaption());
        menu.setParam(MenuItem.PAGENO, data.getPageNo());
        menu.setParam(MenuItem.SECURITY, data.isSecurity() ? "true" : "false");
        menu.setParam(MenuItem.SOFTWARE, data.getVersions());
        menu.setParam(MenuItem.PERMISSION, data.getProccode());
        menu.setParam(MenuItem.PARENT, data.getParent());
        menu.setParam(MenuItem.IMAGE, data.getImage());
        menu.setParam(menu.getId(), data.isWin() && data.isWeb() ? "2" : data.isWin() ? "1" : "0");
    }
}
