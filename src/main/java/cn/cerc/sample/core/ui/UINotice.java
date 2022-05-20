package cn.cerc.sample.core.ui;

import cn.cerc.db.core.ISession;
import cn.cerc.mis.core.AbstractForm;
import cn.cerc.mis.core.HtmlWriter;
import cn.cerc.mis.other.MemoryBuffer;
import cn.cerc.sample.core.BufferUser;
import cn.cerc.ui.core.UIComponent;
import cn.cerc.ui.vcl.UIImage;

public class UINotice extends UIComponent {
    private String cssClass = "info";
    private String text;
    private UIImage icon;

    public UINotice(UIComponent owner) {
        super(owner);
        icon = new UIImage(this);
    }

    @Override
    public void output(HtmlWriter html) {
        if (this.getText() == null) {
            return;
        }
        html.print("<div class='notice'>");
        html.print("<div class='%s'>", cssClass);
        icon.setSrc(String.format("images/public/notice-%s.png", this.cssClass));
        html.print(icon.toString());
        html.print("<span role='conent'>%s</span>", this.getText());
        html.println("</div>");
        html.print("</div>");
    }

    public void info(String text) {
        this.setCssClass("info");
        this.text = text;
    }

    public void warn(String text) {
        this.setCssClass("info");
        this.text = text;
    }

    public void error(String text) {
        this.setCssClass("info");
        this.text = text;
    }

    public static void sendInfo(ISession session, Class<?> clazz, String key, String value) {
        try (MemoryBuffer buff = new MemoryBuffer(BufferUser.Notice_UserCode, session.getUserCode(), clazz.getName(), key)) {
            buff.setValue("level", "info");
            buff.setValue("message", value);
            buff.setExpires(60); // 60秒过期
        }
    }

    public static void sendWarn(ISession session, Class<?> clazz, String key, String value) {
        try (MemoryBuffer buff = new MemoryBuffer(BufferUser.Notice_UserCode, session.getUserCode(), clazz.getName(), key)) {
            buff.setValue("level", "warn");
            buff.setValue("message", value);
            buff.setExpires(60); // 60秒过期
        }
    }

    public static void sendError(ISession session, Class<?> clazz, String key, String value) {
        try (MemoryBuffer buff = new MemoryBuffer(BufferUser.Notice_UserCode, session.getUserCode(), clazz.getName(), key)) {
            buff.setValue("level", "error");
            buff.setValue("message", value);
            buff.setExpires(60); // 60秒过期
        }
    }

    public boolean receive(AbstractForm form, String key) {
        boolean result = false;
        try (MemoryBuffer buff = new MemoryBuffer(BufferUser.Notice_UserCode, form.getSession().getUserCode(), form.getClass().getName(), key)) {
            if (buff.exists("message")) {
                this.setCssClass(buff.getString("level"));
                this.info(buff.getString("message"));
                result = true;
            }
            buff.clear();
        }
        return result;
    }


    public String getText() {
        return text;
    }

}
