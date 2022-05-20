package cn.cerc.sample.forms;

import java.util.Set;

import cn.cerc.mis.client.ServiceSign;

public class AdminServices {
    static DitengServer diteng = new DitengServer().setToken("257d0225506d4c17b671e5b5a5256775");

    /** 用户管理 */
    public static class TAppUserInfo {
        /** 生成新的用户帐号 */
        public static final ServiceSign append = new ServiceSign("TAppUserInfo.append");
        /** 增加登录设备认证码 */
        public static final ServiceSign AppendVerifyCode = new ServiceSign("TAppUserInfo.AppendVerifyCode");
        /** 删除指定的用户帐号 */
        public static final ServiceSign Delete = new ServiceSign("TAppUserInfo.Delete")
                .setProperties(Set.of("UserCode_"));
        /** 删除客服人员 */
        public static final ServiceSign delete_CusService = new ServiceSign("TAppUserInfo.delete_CusService");
        /** 删除指定的用户 */
        public static final ServiceSign DeleteUsers = new ServiceSign("TAppUserInfo.DeleteUsers")
                .setProperties(Set.of("Code_"));
        /** 删除指定用户的设备码，强制下线 */
        public static final ServiceSign DeleteVerifyCode = new ServiceSign("TAppUserInfo.DeleteVerifyCode")
                .setProperties(Set.of("UserCode_", "MachineCode_"));
        /** 从主帐号脱离，保留密保手机 */
        public static final ServiceSign detach = new ServiceSign("TAppUserInfo.detach");
        public static final ServiceSign download = new ServiceSign("TAppUserInfo.download");
        /** 显示用户角色名和帐号 */
        public static final ServiceSign Download_UserRoles = new ServiceSign("TAppUserInfo.Download_UserRoles");
        /** 获取指定的帐号信息 */
        public static final ServiceSign DownloadAccount = new ServiceSign("TAppUserInfo.DownloadAccount")
                .setProperties(Set.of("UserCode_"));
        /** 显示帐号设置基本资料 */
        public static final ServiceSign DownloadAccountSet = new ServiceSign("TAppUserInfo.DownloadAccountSet");
        public static final ServiceSign DownloadAccountVerify = new ServiceSign("TAppUserInfo.DownloadAccountVerify");
        /** 查找当前公司别基本资料 */
        public static final ServiceSign downloadCorp = new ServiceSign("TAppUserInfo.downloadCorp");
        /** 查询客服资料 */
        public static final ServiceSign DownloadCusService = new ServiceSign("TAppUserInfo.DownloadCusService")
                .setProperties(Set.of("Code_"));
        public static final ServiceSign DownloadOptions = new ServiceSign("TAppUserInfo.DownloadOptions")
                .setProperties(Set.of("UserCode_"));
        /** 显示登录设备认证记录 */
        public static final ServiceSign DownloadPrint = new ServiceSign("TAppUserInfo.DownloadPrint");
        /** 查询所有系统角色 */
        public static final ServiceSign DownloadRole = new ServiceSign("TAppUserInfo.DownloadRole");
        /** 显示单个账户基本资料 */
        public static final ServiceSign DownloadSingle = new ServiceSign("TAppUserInfo.DownloadSingle");
        /** 查询指定角色之默认角色 */
        public static final ServiceSign DownloadUserRole = new ServiceSign("TAppUserInfo.DownloadUserRole")
                .setProperties(Set.of("RoleCode_"));
        public static final ServiceSign DownloadVerify = new ServiceSign("TAppUserInfo.DownloadVerify");
        public static final ServiceSign get_Access_Detail = new ServiceSign("TAppUserInfo.get_Access_Detail");
        /** 取得当前用户的手机与QQ号码 */
        public static final ServiceSign get_Account_Detail = new ServiceSign("TAppUserInfo.get_Account_Detail");
        /** 取得当前公司的所有帐号 */
        public static final ServiceSign Get_Code_Name = new ServiceSign("TAppUserInfo.Get_Code_Name");
        /** 取得客服列表 */
        public static final ServiceSign get_CusServicelist = new ServiceSign("TAppUserInfo.get_CusServicelist");
        /** 取得所有已使用或待使用的验证码记录 */
        public static final ServiceSign getAllAvailableVerifyCode = new ServiceSign(
                "TAppUserInfo.getAllAvailableVerifyCode");
        /** 获取帐号表中相同手机号的帐号，以供设置附属关系 */
        public static final ServiceSign getAvaiBelongAccount = new ServiceSign("TAppUserInfo.getAvaiBelongAccount");
        /** 取BelongAccount_栏位为当前帐号的帐套及帐套名称 */
        public static final ServiceSign getBelongAccount = new ServiceSign("TAppUserInfo.getBelongAccount");
        /** 获取当前公司别，可建立用户的最大数量 */
        public static final ServiceSign GetMaxUsers = new ServiceSign("TAppUserInfo.GetMaxUsers");
        /** 取得受委托和委托代理数 */
        public static final ServiceSign getProxyUsers = new ServiceSign("TAppUserInfo.getProxyUsers");
        /** 统计用户的销售/零售业绩 */
        public static final ServiceSign getSalesInfo = new ServiceSign("TAppUserInfo.getSalesInfo");
        /** 取得用户资料 */
        public static final ServiceSign getUserDetail = new ServiceSign("TAppUserInfo.getUserDetail");
        /** 获取用户基本信息和登入登出信息 */
        public static final ServiceSign getUserInfo = new ServiceSign("TAppUserInfo.getUserInfo");
        public static final ServiceSign GetUserList = new ServiceSign("TAppUserInfo.GetUserList");
        /** 修改用户帐号信息 */
        public static final ServiceSign modify = new ServiceSign("TAppUserInfo.modify").setProperties(Set.of("Code_"));
        /** 修改客服人员资料 */
        public static final ServiceSign modify_CusService = new ServiceSign("TAppUserInfo.modify_CusService");
        /** 修改帐套资料 */
        public static final ServiceSign modifyCorp = new ServiceSign("TAppUserInfo.modifyCorp");
        /** 修改登录设备认证记录 */
        public static final ServiceSign ModifyVerifyCode = new ServiceSign("TAppUserInfo.ModifyVerifyCode");
        /** 重置密码并删除用户的手机号码 */
        public static final ServiceSign resetPassword = new ServiceSign("TAppUserInfo.resetPassword")
                .setProperties(Set.of("UserCode_"));
        /** 离职: 将清空帐号的手机号码、邮箱；解除与主帐号的绑定；将密码重置为123456；删除设备码，并停用 */
        public static final ServiceSign resign = new ServiceSign("TAppUserInfo.resign");
        public static final ServiceSign SaveUserOptions = new ServiceSign("TAppUserInfo.SaveUserOptions");
        /** 查询当前公司别的用户操作日志 */
        public static final ServiceSign Search = new ServiceSign("TAppUserInfo.Search");
        /** 查询当前公司别的用户操作日志 */
        public static final ServiceSign Search2 = new ServiceSign("TAppUserInfo.Search2");
        /** 查询指定UKEY的设备安全码 */
        public static final ServiceSign Search3 = new ServiceSign("TAppUserInfo.Search3")
                .setProperties(Set.of("UKEY_"));
        /** 查询当前公司别的客户基本资料 */
        public static final ServiceSign Search_FastCorp = new ServiceSign("TAppUserInfo.Search_FastCorp");
        /** 查询所有的角色代码 */
        public static final ServiceSign Search_UserRole = new ServiceSign("TAppUserInfo.Search_UserRole");
        /** 查询最近的客户服务 */
        public static final ServiceSign SearchCurrentCusService = new ServiceSign(
                "TAppUserInfo.SearchCurrentCusService");
        /** 查询客户服务列表 */
        public static final ServiceSign SearchCusServerList = new ServiceSign("TAppUserInfo.SearchCusServerList");
        /** 搜索指定单别的变更记录 */
        public static final ServiceSign SearchTBChangeLog = new ServiceSign("TAppUserInfo.SearchTBChangeLog");
        /** 开启客户服务帐号 */
        public static final ServiceSign StartCusService = new ServiceSign("TAppUserInfo.StartCusService");
        /** 停止客服帐号 */
        public static final ServiceSign StopCusService = new ServiceSign("TAppUserInfo.StopCusService");
        /** 解锁指定用户 */
        public static final ServiceSign unlock = new ServiceSign("TAppUserInfo.unlock")
                .setProperties(Set.of("UserCode_"));
        /** 更新主从帐号关系 */
        public static final ServiceSign updateBelongAccount = new ServiceSign("TAppUserInfo.updateBelongAccount")
                .setProperties(Set.of("Code_"));
        /** 设置打印设备共享 */
        public static final ServiceSign UpdatePrintShare = new ServiceSign("TAppUserInfo.UpdatePrintShare");
        /** 更新当前用户的职务代理 */
        public static final ServiceSign updateProxy = new ServiceSign("TAppUserInfo.updateProxy")
                .setProperties(Set.of("UserCode_"));
        /** 更新用户头像 */
        public static final ServiceSign updateUserImage = new ServiceSign("TAppUserInfo.updateUserImage");
        /** 统计地藤系统注册企业对云平台的资源占用情况 */
        public static final ServiceSign Use_resource_total = new ServiceSign("TAppUserInfo.Use_resource_total");
        /** 显示当前公司别所有用户 */
        public static final ServiceSign userList = new ServiceSign("TAppUserInfo.userList");
    }

    public static class SvrAnnouncementManage {
        // version 1
        public static final ServiceSign append = new ServiceSign("SvrAnnouncementManage.append");
        public static final ServiceSign download = new ServiceSign("SvrAnnouncementManage.download");
        public static final ServiceSign modify = new ServiceSign("SvrAnnouncementManage.modify");
        public static final ServiceSign modifyState = new ServiceSign("SvrAnnouncementManage.modifyState");
        public static final ServiceSign search = new ServiceSign("SvrAnnouncementManage.search");
    }

    public static class SvrInstall {
        // version 1
        public static final ServiceSign clients = new ServiceSign("SvrInstall.clients");
        public static final ServiceSign download = new ServiceSign("SvrInstall.download");
    }

    public static class TAppCurrentUser {
        // version 1
        public static final ServiceSign Download = new ServiceSign("TAppCurrentUser.Download");
        public static final ServiceSign get_UserLogin_Count = new ServiceSign("TAppCurrentUser.get_UserLogin_Count");
        public static final ServiceSign Search = new ServiceSign("TAppCurrentUser.Search");
        public static final ServiceSign updateLoginScreen = new ServiceSign("TAppCurrentUser.updateLoginScreen");
    }

    public static class TAppDatabase {
        // version 1
        public static final ServiceSign DeleteAccData = new ServiceSign("TAppDatabase.DeleteAccData");
        public static final ServiceSign DeleteCusData = new ServiceSign("TAppDatabase.DeleteCusData");
        public static final ServiceSign DeleteOtherData = new ServiceSign("TAppDatabase.DeleteOtherData");
        public static final ServiceSign DeletePartCode = new ServiceSign("TAppDatabase.DeletePartCode");
        public static final ServiceSign DeleteSupData = new ServiceSign("TAppDatabase.DeleteSupData");
        public static final ServiceSign DeleteTranData = new ServiceSign("TAppDatabase.DeleteTranData");
    }

    public static class TAppHeartbeat {
        // version 1
        public static final ServiceSign GetCurrentUser = new ServiceSign("TAppHeartbeat.GetCurrentUser");
        public static final ServiceSign Hello = new ServiceSign("TAppHeartbeat.Hello");
        public static final ServiceSign KillSession = new ServiceSign("TAppHeartbeat.KillSession");
        public static final ServiceSign stop = new ServiceSign("TAppHeartbeat.stop");
    }

    public static class TAppLogout {
        // version 2
        public static final ServiceSign getLoginTime = new ServiceSign("TAppLogout.getLoginTime");
    }

    public static class TAppMessage {
        // version 1
        public static final ServiceSign append = new ServiceSign("TAppMessage.append");
        public static final ServiceSign download = new ServiceSign("TAppMessage.download");
        public static final ServiceSign readAll = new ServiceSign("TAppMessage.readAll");
        public static final ServiceSign search = new ServiceSign("TAppMessage.search");
        public static final ServiceSign update_state = new ServiceSign("TAppMessage.update_state");
    }

    public static class TAppSysFormDef {
        // version 1
        public static final ServiceSign ExecSQL = new ServiceSign("TAppSysFormDef.ExecSQL");
        public static final ServiceSign ResetCusPart = new ServiceSign("TAppSysFormDef.ResetCusPart");
        public static final ServiceSign search = new ServiceSign("TAppSysFormDef.search");
    }

    public static class TAppUserAccesss {
        // version 1
        public static final ServiceSign Append = new ServiceSign("TAppUserAccesss.Append");
        public static final ServiceSign Delete = new ServiceSign("TAppUserAccesss.Delete");
        public static final ServiceSign GetProcList = new ServiceSign("TAppUserAccesss.GetProcList");
        public static final ServiceSign Modify = new ServiceSign("TAppUserAccesss.Modify");
        public static final ServiceSign Save = new ServiceSign("TAppUserAccesss.Save");
        public static final ServiceSign SearchUserRoles = new ServiceSign("TAppUserAccesss.SearchUserRoles");
        public static final ServiceSign UpdateToDefault = new ServiceSign("TAppUserAccesss.UpdateToDefault");
        public static final ServiceSign UpdateToDiy = new ServiceSign("TAppUserAccesss.UpdateToDiy");
    }

    public static class TAppVineOptions {
        // version 1
        public static final ServiceSign download = new ServiceSign("TAppVineOptions.download");
        public static final ServiceSign Download = new ServiceSign("TAppVineOptions.Download");
        public static final ServiceSign Save = new ServiceSign("TAppVineOptions.Save");
        public static final ServiceSign saveOption = new ServiceSign("TAppVineOptions.saveOption");
    }

    public static class TAppWelcome {
        // version 1
        public static final ServiceSign GetCurTime = new ServiceSign("TAppWelcome.GetCurTime");
        public static final ServiceSign GetUserInfo = new ServiceSign("TAppWelcome.GetUserInfo");
    }

    public static class SvrCheckDraftTB {
        // version 3
        public static final ServiceSign detail = new ServiceSign("SvrCheckDraftTB.detail");
        public static final ServiceSign getDraftNum = new ServiceSign("SvrCheckDraftTB.getDraftNum");
        public static final ServiceSign search = new ServiceSign("SvrCheckDraftTB.search");
    }

    public static class TAppTaskTranAR {
        // version 1
        public static final ServiceSign calARYM = new ServiceSign("TAppTaskTranAR.calARYM");
        public static final ServiceSign carryForwardAR = new ServiceSign("TAppTaskTranAR.carryForwardAR");
        public static final ServiceSign carryForwardAS = new ServiceSign("TAppTaskTranAR.carryForwardAS");
        public static final ServiceSign carryForwardObjType = new ServiceSign("TAppTaskTranAR.carryForwardObjType");
    }

    public static class TAppTaskTranAP {
        // version 1
        public static final ServiceSign calAPYM = new ServiceSign("TAppTaskTranAP.calAPYM");
        public static final ServiceSign carryForwardAP = new ServiceSign("TAppTaskTranAP.carryForwardAP");
    }

    public static class TAppAccAmount {
        // version 1
        public static final ServiceSign calAccYM = new ServiceSign("TAppAccAmount.calAccYM");
        public static final ServiceSign carryForwardAcc = new ServiceSign("TAppAccAmount.carryForwardAcc");
    }

    public static class TAppCreditLine {
        // version 1
        public static final ServiceSign calCusCreditLimit = new ServiceSign("TAppCreditLine.calCusCreditLimit");
        public static final ServiceSign updateCC = new ServiceSign("TAppCreditLine.updateCC");
        public static final ServiceSign updateCurrencyRate = new ServiceSign("TAppCreditLine.updateCurrencyRate");
        public static final ServiceSign updateCusCreditLimit = new ServiceSign("TAppCreditLine.updateCusCreditLimit");
        public static final ServiceSign updateSupplyH = new ServiceSign("TAppCreditLine.updateSupplyH");
    }

    public static class TAppResetCostUP {
        // version 1
        public static final ServiceSign RestoreTranInUPToCostUP = new ServiceSign(
                "TAppResetCostUP.RestoreTranInUPToCostUP");
        public static final ServiceSign updateCurrentMonthProfit = new ServiceSign(
                "TAppResetCostUP.updateCurrentMonthProfit");
    }

    public static class TAppSysDataCheck {
        // version 1
        public static final ServiceSign checkPassWord = new ServiceSign("TAppSysDataCheck.checkPassWord");
        public static final ServiceSign getVerifyInfo = new ServiceSign("TAppSysDataCheck.getVerifyInfo");
        public static final ServiceSign getVerifyInfo_detail = new ServiceSign("TAppSysDataCheck.getVerifyInfo_detail");
    }

    public static class TAppReport {
        // version 1
        public static final ServiceSign Add_ReportPublic = new ServiceSign("TAppReport.Add_ReportPublic");
        public static final ServiceSign Append = new ServiceSign("TAppReport.Append");
        public static final ServiceSign Del_ReportPublic = new ServiceSign("TAppReport.Del_ReportPublic");
        public static final ServiceSign Delete = new ServiceSign("TAppReport.Delete");
        public static final ServiceSign DownloadList = new ServiceSign("TAppReport.DownloadList");
        public static final ServiceSign FastPrintReport = new ServiceSign("TAppReport.FastPrintReport");
        public static final ServiceSign Modify = new ServiceSign("TAppReport.Modify");
        public static final ServiceSign MultPrintTimes = new ServiceSign("TAppReport.MultPrintTimes");
        public static final ServiceSign SaveLastReport = new ServiceSign("TAppReport.SaveLastReport");
        public static final ServiceSign Search_Edit = new ServiceSign("TAppReport.Search_Edit");
        public static final ServiceSign Search_HWAReportList = new ServiceSign("TAppReport.Search_HWAReportList");
        public static final ServiceSign Search_ReportDevice1 = new ServiceSign("TAppReport.Search_ReportDevice1");
        public static final ServiceSign Search_ReportDevice2 = new ServiceSign("TAppReport.Search_ReportDevice2");
        public static final ServiceSign Search_ReportNoFinal = new ServiceSign("TAppReport.Search_ReportNoFinal");
        public static final ServiceSign Search_ReportPublic = new ServiceSign("TAppReport.Search_ReportPublic");
        public static final ServiceSign searchFastPrint = new ServiceSign("TAppReport.searchFastPrint");
        public static final ServiceSign SearchReport = new ServiceSign("TAppReport.SearchReport");
        public static final ServiceSign Set_DefaultReport = new ServiceSign("TAppReport.Set_DefaultReport");
    }

    public static class ApiUserInfo {
        // version 1
        public static final ServiceSign checkMobile = new ServiceSign("ApiUserInfo.checkMobile");
        public static final ServiceSign delete = new ServiceSign("ApiUserInfo.delete");
        public static final ServiceSign getAdminMobiles = new ServiceSign("ApiUserInfo.getAdminMobiles");
        public static final ServiceSign getAllSales = new ServiceSign("ApiUserInfo.getAllSales");
        public static final ServiceSign getBelongCorpCode = new ServiceSign("ApiUserInfo.getBelongCorpCode");
        public static final ServiceSign getCodeByName = new ServiceSign("ApiUserInfo.getCodeByName");
        public static final ServiceSign getCodeByUID = new ServiceSign("ApiUserInfo.getCodeByUID");
        public static final ServiceSign getMasterAccount = new ServiceSign("ApiUserInfo.getMasterAccount");
        public static final ServiceSign getUserInfo = new ServiceSign("ApiUserInfo.getUserInfo");
        public static final ServiceSign loadList = new ServiceSign("ApiUserInfo.loadList");
        public static final ServiceSign searchAllDept = new ServiceSign("ApiUserInfo.searchAllDept");
        public static final ServiceSign searchDept = new ServiceSign("ApiUserInfo.searchDept");
        public static final ServiceSign searchStaff = new ServiceSign("ApiUserInfo.searchStaff");
        public static final ServiceSign updatePwd = new ServiceSign("ApiUserInfo.updatePwd");
        public static final ServiceSign updateRole = new ServiceSign("ApiUserInfo.updateRole");
    }

    public static class TAppParameterAcc {
        // version 1
        public static final ServiceSign getParamDetail = new ServiceSign("TAppParameterAcc.getParamDetail");
        public static final ServiceSign search = new ServiceSign("TAppParameterAcc.search");
    }

    public static class TAppSendMsg {
        // version 1
        public static final ServiceSign search = new ServiceSign("TAppSendMsg.search");
    }

    public static class ApiOurInfo {
        // version 1
        public static final ServiceSign appendToCorpOption = new ServiceSign("ApiOurInfo.appendToCorpOption");
        public static final ServiceSign checkManagerPhone = new ServiceSign("ApiOurInfo.checkManagerPhone");
        public static final ServiceSign delete = new ServiceSign("ApiOurInfo.delete");
        public static final ServiceSign getAllianceInfo = new ServiceSign("ApiOurInfo.getAllianceInfo");
        public static final ServiceSign getBookCreateDate = new ServiceSign("ApiOurInfo.getBookCreateDate");
        public static final ServiceSign getCtFreeCorpNo = new ServiceSign("ApiOurInfo.getCtFreeCorpNo");
        public static final ServiceSign getDeleteCorpNo = new ServiceSign("ApiOurInfo.getDeleteCorpNo");
        public static final ServiceSign getOurInfoByCorpNo = new ServiceSign("ApiOurInfo.getOurInfoByCorpNo");
        public static final ServiceSign getTaskCorpNo = new ServiceSign("ApiOurInfo.getTaskCorpNo");
        public static final ServiceSign getVineOptionsByCode = new ServiceSign("ApiOurInfo.getVineOptionsByCode");
        public static final ServiceSign updateToCorpOption = new ServiceSign("ApiOurInfo.updateToCorpOption");
    }

    public static class TAppVipPayTB {
        // version 1
        public static final ServiceSign AppendDetail = new ServiceSign("TAppVipPayTB.AppendDetail");
        public static final ServiceSign DeleteDetail = new ServiceSign("TAppVipPayTB.DeleteDetail");
        public static final ServiceSign ModifyDetail = new ServiceSign("TAppVipPayTB.ModifyDetail");
        public static final ServiceSign search = new ServiceSign("TAppVipPayTB.search");
        public static final ServiceSign SearchDetail = new ServiceSign("TAppVipPayTB.SearchDetail");
    }

    public static class ApiToken {
        // version 1
        public static final ServiceSign getToken = new ServiceSign("ApiToken.getToken");
        public static final ServiceSign updateToken = new ServiceSign("ApiToken.updateToken");
    }

    public static class SvrAccountManage {
        // version 1
        public static final ServiceSign download = new ServiceSign("SvrAccountManage.download");
        public static final ServiceSign getUserList = new ServiceSign("SvrAccountManage.getUserList");
        public static final ServiceSign modify = new ServiceSign("SvrAccountManage.modify");
        public static final ServiceSign modifySecretMobile = new ServiceSign("SvrAccountManage.modifySecretMobile");
        public static final ServiceSign resetPassword = new ServiceSign("SvrAccountManage.resetPassword");
        public static final ServiceSign search = new ServiceSign("SvrAccountManage.search");
        public static final ServiceSign unlock = new ServiceSign("SvrAccountManage.unlock");
    }

    public static class TAppUserLog {
        // version 1
        public static final ServiceSign GetLoginCount = new ServiceSign("TAppUserLog.GetLoginCount");
        public static final ServiceSign register_UserLog = new ServiceSign("TAppUserLog.register_UserLog");
    }

    public static class SvrClientManage {
        // version 1
        public static final ServiceSign append = new ServiceSign("SvrClientManage.append");
        public static final ServiceSign checkProject = new ServiceSign("SvrClientManage.checkProject");
        public static final ServiceSign delete = new ServiceSign("SvrClientManage.delete");
        public static final ServiceSign modify = new ServiceSign("SvrClientManage.modify");
        public static final ServiceSign search = new ServiceSign("SvrClientManage.search");
    }

    public static class SvrClientVersion {
        // version 1
        public static final ServiceSign append = new ServiceSign("SvrClientVersion.append");
        public static final ServiceSign delete = new ServiceSign("SvrClientVersion.delete");
        public static final ServiceSign modify = new ServiceSign("SvrClientVersion.modify");
        public static final ServiceSign modifyApple = new ServiceSign("SvrClientVersion.modifyApple");
        public static final ServiceSign search = new ServiceSign("SvrClientVersion.search");
    }

    public static class SvrERPWarnb {
        // version 1
        public static final ServiceSign append = new ServiceSign("SvrERPWarnb.append");
        public static final ServiceSign appendAuthorize = new ServiceSign("SvrERPWarnb.appendAuthorize");
        public static final ServiceSign checkAuthorize = new ServiceSign("SvrERPWarnb.checkAuthorize");
        public static final ServiceSign deleteAuthorize = new ServiceSign("SvrERPWarnb.deleteAuthorize");
        public static final ServiceSign download = new ServiceSign("SvrERPWarnb.download");
        public static final ServiceSign downloadPermission = new ServiceSign("SvrERPWarnb.downloadPermission");
        public static final ServiceSign getByCorpNo = new ServiceSign("SvrERPWarnb.getByCorpNo");
        public static final ServiceSign getOneByCorpNo = new ServiceSign("SvrERPWarnb.getOneByCorpNo");
        public static final ServiceSign getSQL = new ServiceSign("SvrERPWarnb.getSQL");
        public static final ServiceSign modify = new ServiceSign("SvrERPWarnb.modify");
        public static final ServiceSign search = new ServiceSign("SvrERPWarnb.search");
        public static final ServiceSign selectBookInfo = new ServiceSign("SvrERPWarnb.selectBookInfo");
    }

    public static class SvrERPWarnData {
        // version 1
        public static final ServiceSign append = new ServiceSign("SvrERPWarnData.append");
        public static final ServiceSign getData = new ServiceSign("SvrERPWarnData.getData");
        public static final ServiceSign getFieldContrast = new ServiceSign("SvrERPWarnData.getFieldContrast");
        public static final ServiceSign search = new ServiceSign("SvrERPWarnData.search");
    }

    public static class SvrERPWarnManage {
        // version 1
        public static final ServiceSign appendWarnh = new ServiceSign("SvrERPWarnManage.appendWarnh");
        public static final ServiceSign downloadWarnh = new ServiceSign("SvrERPWarnManage.downloadWarnh");
        public static final ServiceSign mofidyWarnh = new ServiceSign("SvrERPWarnManage.mofidyWarnh");
        public static final ServiceSign search = new ServiceSign("SvrERPWarnManage.search");
    }

    public static class SvrIndustry {
        // version 2
        public static final ServiceSign append = new ServiceSign("SvrIndustry.append");
        public static final ServiceSign appendMenu = new ServiceSign("SvrIndustry.appendMenu");
        public static final ServiceSign delete = new ServiceSign("SvrIndustry.delete");
        public static final ServiceSign deleteMenu = new ServiceSign("SvrIndustry.deleteMenu");
        public static final ServiceSign getAllMenu = new ServiceSign("SvrIndustry.getAllMenu");
        public static final ServiceSign getMenuGroup = new ServiceSign("SvrIndustry.getMenuGroup");
        public static final ServiceSign modify = new ServiceSign("SvrIndustry.modify");
        public static final ServiceSign saveGroup = new ServiceSign("SvrIndustry.saveGroup");
        public static final ServiceSign saveMeun = new ServiceSign("SvrIndustry.saveMeun");
        public static final ServiceSign search = new ServiceSign("SvrIndustry.search");
        public static final ServiceSign searchDefaultMenu = new ServiceSign("SvrIndustry.searchDefaultMenu");
    }

    public static class SvrPlantManage {
        // version 1
        public static final ServiceSign append = new ServiceSign("SvrPlantManage.append");
        public static final ServiceSign download = new ServiceSign("SvrPlantManage.download");
        public static final ServiceSign mofidy = new ServiceSign("SvrPlantManage.mofidy");
        public static final ServiceSign search = new ServiceSign("SvrPlantManage.search");
    }

    public static class SvrMyPrinterInfo {
        // version 3
        public static final ServiceSign append = new ServiceSign("SvrMyPrinterInfo.append");
        public static final ServiceSign delete = new ServiceSign("SvrMyPrinterInfo.delete");
        public static final ServiceSign download = new ServiceSign("SvrMyPrinterInfo.download");
        public static final ServiceSign modify = new ServiceSign("SvrMyPrinterInfo.modify");
    }

    public static class TAppOurInfo {
        // version 1
        public static final ServiceSign add_FastMail = new ServiceSign("TAppOurInfo.add_FastMail");
        public static final ServiceSign Append = new ServiceSign("TAppOurInfo.Append");
        public static final ServiceSign appendERPToken = new ServiceSign("TAppOurInfo.appendERPToken");
        public static final ServiceSign AppointCusService = new ServiceSign("TAppOurInfo.AppointCusService");
        public static final ServiceSign Delete = new ServiceSign("TAppOurInfo.Delete");
        public static final ServiceSign deleteERPToken = new ServiceSign("TAppOurInfo.deleteERPToken");
        public static final ServiceSign DisableOurCode = new ServiceSign("TAppOurInfo.DisableOurCode");
        public static final ServiceSign Download = new ServiceSign("TAppOurInfo.Download");
        public static final ServiceSign download_CorpNo_TBBalance = new ServiceSign(
                "TAppOurInfo.download_CorpNo_TBBalance");
        public static final ServiceSign downloadERPToken = new ServiceSign("TAppOurInfo.downloadERPToken");
        public static final ServiceSign DownloadSingle = new ServiceSign("TAppOurInfo.DownloadSingle");
        public static final ServiceSign Get_Status = new ServiceSign("TAppOurInfo.Get_Status");
        public static final ServiceSign Get_TotalCusAndSup = new ServiceSign("TAppOurInfo.Get_TotalCusAndSup");
        public static final ServiceSign GetCorpNo = new ServiceSign("TAppOurInfo.GetCorpNo");
        public static final ServiceSign GetCorpOptions = new ServiceSign("TAppOurInfo.GetCorpOptions");
        public static final ServiceSign getCtFreeCorpNo = new ServiceSign("TAppOurInfo.getCtFreeCorpNo");
        public static final ServiceSign getMaxRegisterNum = new ServiceSign("TAppOurInfo.getMaxRegisterNum");
        public static final ServiceSign Modify = new ServiceSign("TAppOurInfo.Modify");
        public static final ServiceSign modifyERPToken = new ServiceSign("TAppOurInfo.modifyERPToken");
        public static final ServiceSign modifyOurInfo = new ServiceSign("TAppOurInfo.modifyOurInfo");
        public static final ServiceSign searchStopCorp = new ServiceSign("TAppOurInfo.searchStopCorp");
        public static final ServiceSign setCategory = new ServiceSign("TAppOurInfo.setCategory");
        public static final ServiceSign Update_ERP_ScanBC = new ServiceSign("TAppOurInfo.Update_ERP_ScanBC");
        public static final ServiceSign Update_OurInfo = new ServiceSign("TAppOurInfo.Update_OurInfo");
        // version 2
        public static final ServiceSign getBookReadme = new ServiceSign("TAppOurInfo.getBookReadme");
        public static final ServiceSign getIndustry = new ServiceSign("TAppOurInfo.getIndustry");
        public static final ServiceSign updateBookReadme = new ServiceSign("TAppOurInfo.updateBookReadme");
    }

    public static class SvrSendPrint {
        // version 1
        public static final ServiceSign copyReport = new ServiceSign("SvrSendPrint.copyReport");
        public static final ServiceSign downloadPrint = new ServiceSign("SvrSendPrint.downloadPrint");
        public static final ServiceSign getAliMessage = new ServiceSign("SvrSendPrint.getAliMessage");
        public static final ServiceSign getMachineData = new ServiceSign("SvrSendPrint.getMachineData");
        public static final ServiceSign getReportHead = new ServiceSign("SvrSendPrint.getReportHead");
        public static final ServiceSign getReportHeight = new ServiceSign("SvrSendPrint.getReportHeight");
        public static final ServiceSign getReportList = new ServiceSign("SvrSendPrint.getReportList");
        public static final ServiceSign getReportUrl = new ServiceSign("SvrSendPrint.getReportUrl");
        public static final ServiceSign save = new ServiceSign("SvrSendPrint.save");
        public static final ServiceSign saveLastReport = new ServiceSign("SvrSendPrint.saveLastReport");
        public static final ServiceSign searchReport = new ServiceSign("SvrSendPrint.searchReport");
        public static final ServiceSign searchReportPublic = new ServiceSign("SvrSendPrint.searchReportPublic");
        public static final ServiceSign setPrinter = new ServiceSign("SvrSendPrint.setPrinter");
    }

    public static class SvrLinkShop {
        // version 1
        public static final ServiceSign adminModify = new ServiceSign("SvrLinkShop.adminModify");
        public static final ServiceSign append = new ServiceSign("SvrLinkShop.append");
        public static final ServiceSign deleteImage = new ServiceSign("SvrLinkShop.deleteImage");
        public static final ServiceSign download = new ServiceSign("SvrLinkShop.download");
        public static final ServiceSign modify = new ServiceSign("SvrLinkShop.modify");
        public static final ServiceSign saveImage = new ServiceSign("SvrLinkShop.saveImage");
        public static final ServiceSign search = new ServiceSign("SvrLinkShop.search");
    }

    public static class SvrMenuOrder {
        // version 1
        public static final ServiceSign detail = new ServiceSign("SvrMenuOrder.detail");
        public static final ServiceSign search = new ServiceSign("SvrMenuOrder.search");
    }

    public static class SvrForgetPassword {
        // version 1
        public static final ServiceSign sendVerifyCode = new ServiceSign("SvrForgetPassword.sendVerifyCode");
        public static final ServiceSign update = new ServiceSign("SvrForgetPassword.update");
    }

    public static class SvrModifyPassword {
        // version 1
        public static final ServiceSign update = new ServiceSign("SvrModifyPassword.update");
    }

    public static class SvrModuleLink {
        // version 1
        public static final ServiceSign append = new ServiceSign("SvrModuleLink.append");
        public static final ServiceSign delete = new ServiceSign("SvrModuleLink.delete");
        public static final ServiceSign download = new ServiceSign("SvrModuleLink.download");
        public static final ServiceSign modify = new ServiceSign("SvrModuleLink.modify");
        public static final ServiceSign search = new ServiceSign("SvrModuleLink.search");
    }

    public static class SvrModuleManual {
        // version 1
        public static final ServiceSign modify = new ServiceSign("SvrModuleManual.modify");
        public static final ServiceSign search = new ServiceSign("SvrModuleManual.search");
    }

    public static class SvrMoneyReview {
        // version 1
        public static final ServiceSign search = new ServiceSign("SvrMoneyReview.search");
        public static final ServiceSign transfer = new ServiceSign("SvrMoneyReview.transfer");
    }

    public static class SvrRemotePrint {
        // version 1
        public static final ServiceSign finishPrint = new ServiceSign("SvrRemotePrint.finishPrint");
        public static final ServiceSign getReportData = new ServiceSign("SvrRemotePrint.getReportData");
        public static final ServiceSign getReportList = new ServiceSign("SvrRemotePrint.getReportList");
        public static final ServiceSign getReportType = new ServiceSign("SvrRemotePrint.getReportType");
        public static final ServiceSign saveDeviceInfo = new ServiceSign("SvrRemotePrint.saveDeviceInfo");
        public static final ServiceSign saveReportData = new ServiceSign("SvrRemotePrint.saveReportData");
        public static final ServiceSign saveReportTemplates = new ServiceSign("SvrRemotePrint.saveReportTemplates");
    }

    public static class SvrReport {
        // version 1
        public static final ServiceSign download = new ServiceSign("SvrReport.download");
        public static final ServiceSign getOrderedReportNum = new ServiceSign("SvrReport.getOrderedReportNum");
    }

    public static class SvrSearchMenu {
        // version 1
        public static final ServiceSign collectMenu = new ServiceSign("SvrSearchMenu.collectMenu");
        public static final ServiceSign search = new ServiceSign("SvrSearchMenu.search");
    }

    public static class TAppCharge {
        // version 1
        public static final ServiceSign Append = new ServiceSign("TAppCharge.Append");
        public static final ServiceSign ChargesAppend = new ServiceSign("TAppCharge.ChargesAppend");
        public static final ServiceSign ChargesDelete = new ServiceSign("TAppCharge.ChargesDelete");
        public static final ServiceSign clientBill = new ServiceSign("TAppCharge.clientBill");
        public static final ServiceSign clientBillDetailE = new ServiceSign("TAppCharge.clientBillDetailE");
        public static final ServiceSign clientBillDetailP = new ServiceSign("TAppCharge.clientBillDetailP");
        public static final ServiceSign Delete = new ServiceSign("TAppCharge.Delete");
        public static final ServiceSign ExamineAll = new ServiceSign("TAppCharge.ExamineAll");
        public static final ServiceSign ExaminePay = new ServiceSign("TAppCharge.ExaminePay");
        public static final ServiceSign ExpenseAppend = new ServiceSign("TAppCharge.ExpenseAppend");
        public static final ServiceSign ExpenseCancel = new ServiceSign("TAppCharge.ExpenseCancel");
        public static final ServiceSign ExpenseDelete = new ServiceSign("TAppCharge.ExpenseDelete");
        public static final ServiceSign ExpenseExamine = new ServiceSign("TAppCharge.ExpenseExamine");
        public static final ServiceSign ExpenseModify = new ServiceSign("TAppCharge.ExpenseModify");
        public static final ServiceSign ExpenseSearch = new ServiceSign("TAppCharge.ExpenseSearch");
        public static final ServiceSign getChargeList = new ServiceSign("TAppCharge.getChargeList");
        public static final ServiceSign getOrderMenu = new ServiceSign("TAppCharge.getOrderMenu");
        public static final ServiceSign Maintain = new ServiceSign("TAppCharge.Maintain");
        public static final ServiceSign Modify = new ServiceSign("TAppCharge.Modify");
        public static final ServiceSign PayCancel = new ServiceSign("TAppCharge.PayCancel");
        public static final ServiceSign PaymentList = new ServiceSign("TAppCharge.PaymentList");
        public static final ServiceSign PaymentSearch = new ServiceSign("TAppCharge.PaymentSearch");
        public static final ServiceSign QueryCharge = new ServiceSign("TAppCharge.QueryCharge");
        public static final ServiceSign SearchPay = new ServiceSign("TAppCharge.SearchPay");
        public static final ServiceSign serviceBill = new ServiceSign("TAppCharge.serviceBill");
        public static final ServiceSign serviceBillDetailE = new ServiceSign("TAppCharge.serviceBillDetailE");
        public static final ServiceSign serviceBillDetailP = new ServiceSign("TAppCharge.serviceBillDetailP");
        public static final ServiceSign Standard = new ServiceSign("TAppCharge.Standard");
        public static final ServiceSign Standard2 = new ServiceSign("TAppCharge.Standard2");
        public static final ServiceSign urgePayment = new ServiceSign("TAppCharge.urgePayment");
        // version 2
        public static final ServiceSign accountAdjustment = new ServiceSign("TAppCharge.accountAdjustment");
        public static final ServiceSign getOurInfoStatus = new ServiceSign("TAppCharge.getOurInfoStatus");
    }

    public static class SvrUserInfoManage {
        // version 1
        public static final ServiceSign download = new ServiceSign("SvrUserInfoManage.download");
        public static final ServiceSign modify = new ServiceSign("SvrUserInfoManage.modify");
        public static final ServiceSign userList = new ServiceSign("SvrUserInfoManage.userList");
    }

    public static class TAppTBOptions {
        public static final ServiceSign addTB = new ServiceSign("TAppTBOptions.addTB");
        public static final ServiceSign Append = new ServiceSign("TAppTBOptions.Append");
        public static final ServiceSign appendFlowUser = new ServiceSign("TAppTBOptions.appendFlowUser");
        public static final ServiceSign deleteFlowUser = new ServiceSign("TAppTBOptions.deleteFlowUser");
        public static final ServiceSign DeptDefault = new ServiceSign("TAppTBOptions.DeptDefault");
        public static final ServiceSign Download = new ServiceSign("TAppTBOptions.Download");
        public static final ServiceSign DownloadFlowUser = new ServiceSign("TAppTBOptions.DownloadFlowUser");
        public static final ServiceSign GetAllowDraftPrint = new ServiceSign("TAppTBOptions.GetAllowDraftPrint");
        public static final ServiceSign Modify = new ServiceSign("TAppTBOptions.Modify");
        public static final ServiceSign modifyFlowUser = new ServiceSign("TAppTBOptions.modifyFlowUser");
        public static final ServiceSign Search = new ServiceSign("TAppTBOptions.Search");
        public static final ServiceSign searchFlowUsers = new ServiceSign("TAppTBOptions.searchFlowUsers");
        public static final ServiceSign workflowEnabled = new ServiceSign("TAppTBOptions.workflowEnabled")
                .setProperties(Set.of("tb"));
    }

    public static class SvrSystemMenu {
        /** 统计系统模组的菜单数据 */
        public static final ServiceSign getModuleGroup = new ServiceSign("SvrSystemMenu.getModuleGroup").setVersion(3);
        // version 1
        public static final ServiceSign append = new ServiceSign("SvrSystemMenu.append");
        public static final ServiceSign download = new ServiceSign("SvrSystemMenu.download");
        public static final ServiceSign modify = new ServiceSign("SvrSystemMenu.modify");
        public static final ServiceSign orderDetail = new ServiceSign("SvrSystemMenu.orderDetail");
        public static final ServiceSign search = new ServiceSign("SvrSystemMenu.search");
        public static final ServiceSign updateMenuIconType = new ServiceSign("SvrSystemMenu.updateMenuIconType");
    }

    public static class TAppPayLog {
        // version 1
        public static final ServiceSign append = new ServiceSign("TAppPayLog.append");
        public static final ServiceSign modify = new ServiceSign("TAppPayLog.modify");
        public static final ServiceSign search = new ServiceSign("TAppPayLog.search");
    }

    public static class TAppPay {
        // version 1
        public static final ServiceSign append = new ServiceSign("TAppPay.append");
    }

    public static class TAppSysProc {
        // version 1
        public static final ServiceSign detail = new ServiceSign("TAppSysProc.detail");
        public static final ServiceSign search = new ServiceSign("TAppSysProc.search");
    }

    public static class TAdminLinkCard {
        // version 1
        public static final ServiceSign download = new ServiceSign("TAdminLinkCard.download");
    }

    public static class TAppOrderMenu {
        // version 1
        public static final ServiceSign getMenuList = new ServiceSign("TAppOrderMenu.getMenuList");
        public static final ServiceSign getModules = new ServiceSign("TAppOrderMenu.getModules");
        public static final ServiceSign order = new ServiceSign("TAppOrderMenu.order");
        public static final ServiceSign unorder = new ServiceSign("TAppOrderMenu.unorder");
    }

    public static class SvrTimeOutManage {
        // version 1
        public static final ServiceSign delete = new ServiceSign("SvrTimeOutManage.delete");
        public static final ServiceSign list = new ServiceSign("SvrTimeOutManage.list");
        public static final ServiceSign search = new ServiceSign("SvrTimeOutManage.search");
    }

    public static class TAppMenuFile {
        // version 3
        public static final ServiceSign download = new ServiceSign("TAppMenuFile.download");
    }

    public static class TAppTaskCharge {
        // version 1
        public static final ServiceSign autoMakeCharging = new ServiceSign("TAppTaskCharge.autoMakeCharging");
        public static final ServiceSign createMonthlyOrderRecord = new ServiceSign(
                "TAppTaskCharge.createMonthlyOrderRecord");
    }

    public static class SvrSession {
        // version 1
        public static final ServiceSign byToken = new ServiceSign("SvrSession.byToken");
        public static final ServiceSign byUserCode = new ServiceSign("SvrSession.byUserCode");
    }

    public static class ApiLinkCard {
        // version 1
        public static final ServiceSign check = new ServiceSign("ApiLinkCard.check");
        public static final ServiceSign cusLinkActive = new ServiceSign("ApiLinkCard.cusLinkActive");
        public static final ServiceSign deleteLink = new ServiceSign("ApiLinkCard.deleteLink");
        public static final ServiceSign delLinkByCardNo = new ServiceSign("ApiLinkCard.delLinkByCardNo");
        public static final ServiceSign delLinkBySupCardNo = new ServiceSign("ApiLinkCard.delLinkBySupCardNo");
        public static final ServiceSign getAlliance = new ServiceSign("ApiLinkCard.getAlliance");
        public static final ServiceSign getGreenAndRedLink = new ServiceSign("ApiLinkCard.getGreenAndRedLink");
        public static final ServiceSign getLinkByCusCode = new ServiceSign("ApiLinkCard.getLinkByCusCode");
        public static final ServiceSign getLinkByDownCusCode = new ServiceSign("ApiLinkCard.getLinkByDownCusCode");
        public static final ServiceSign getLinkBySupCode = new ServiceSign("ApiLinkCard.getLinkBySupCode");
        public static final ServiceSign getLinkBySupCodeCardNo = new ServiceSign("ApiLinkCard.getLinkBySupCodeCardNo");
        public static final ServiceSign getLinkByUpSupCode = new ServiceSign("ApiLinkCard.getLinkByUpSupCode");
        public static final ServiceSign getLinkCard = new ServiceSign("ApiLinkCard.getLinkCard");
        public static final ServiceSign getLinkCardByCardNo = new ServiceSign("ApiLinkCard.getLinkCardByCardNo");
        public static final ServiceSign getLinkInfo = new ServiceSign("ApiLinkCard.getLinkInfo");
        public static final ServiceSign getLinkTypeBySupCorpNo = new ServiceSign("ApiLinkCard.getLinkTypeBySupCorpNo");
        public static final ServiceSign getOtherAlliance = new ServiceSign("ApiLinkCard.getOtherAlliance");
        public static final ServiceSign getRedLinkCard = new ServiceSign("ApiLinkCard.getRedLinkCard");
        public static final ServiceSign isLink = new ServiceSign("ApiLinkCard.isLink");
        public static final ServiceSign supLinkActive = new ServiceSign("ApiLinkCard.supLinkActive");
        public static final ServiceSign updateDownCusCode = new ServiceSign("ApiLinkCard.updateDownCusCode");
        public static final ServiceSign updateUpSupCode = new ServiceSign("ApiLinkCard.updateUpSupCode");
        public static final ServiceSign validateCusCorpNo = new ServiceSign("ApiLinkCard.validateCusCorpNo");
    }

    public static class ApiMenus {
        // version 1
        public static final ServiceSign appOrderMenu = new ServiceSign("ApiMenus.appOrderMenu");
        public static final ServiceSign getAllModules = new ServiceSign("ApiMenus.getAllModules");
        public static final ServiceSign getModule = new ServiceSign("ApiMenus.getModule");
        public static final ServiceSign getModuleLinks = new ServiceSign("ApiMenus.getModuleLinks");
        // version 3
        public static final ServiceSign getMenus = new ServiceSign("ApiMenus.getMenus");
    }

    public static class TAppLinkCard {
        // version 1
        public static final ServiceSign Append = new ServiceSign("TAppLinkCard.Append");
        public static final ServiceSign batchAppend = new ServiceSign("TAppLinkCard.batchAppend");
        public static final ServiceSign CusLinkActive = new ServiceSign("TAppLinkCard.CusLinkActive");
        public static final ServiceSign delete = new ServiceSign("TAppLinkCard.delete");
        public static final ServiceSign Delete = new ServiceSign("TAppLinkCard.Delete");
        public static final ServiceSign Download = new ServiceSign("TAppLinkCard.Download");
        public static final ServiceSign downloadCusNotLogin = new ServiceSign("TAppLinkCard.downloadCusNotLogin");
        public static final ServiceSign downloadCusUseClient = new ServiceSign("TAppLinkCard.downloadCusUseClient");
        public static final ServiceSign GetAreaList = new ServiceSign("TAppLinkCard.GetAreaList");
        public static final ServiceSign getdeInfo = new ServiceSign("TAppLinkCard.getdeInfo");
        public static final ServiceSign Modify = new ServiceSign("TAppLinkCard.Modify");
        public static final ServiceSign SetCusLink = new ServiceSign("TAppLinkCard.SetCusLink");
        public static final ServiceSign SetSupLink = new ServiceSign("TAppLinkCard.SetSupLink");
        public static final ServiceSign SupLinkActive = new ServiceSign("TAppLinkCard.SupLinkActive");
        public static final ServiceSign UpdateCusArea = new ServiceSign("TAppLinkCard.UpdateCusArea");
    }

    /** 更新日志 */
    public static class SvrSoftwareUpdateManage {
        public static final ServiceSign append = new ServiceSign("SvrSoftwareUpdateManage.append");
        public static final ServiceSign delete = new ServiceSign("SvrSoftwareUpdateManage.delete");
        /** 获取指定语言的更新日志 */
        public static final ServiceSign download = new ServiceSign("SvrSoftwareUpdateManage.download").setVersion(3)
                .setProperties(Set.of("Language_"));
        public static final ServiceSign modify = new ServiceSign("SvrSoftwareUpdateManage.modify");
        public static final ServiceSign search = new ServiceSign("SvrSoftwareUpdateManage.search");
    }

    /**
     * from mall.jar
     */
    public static class TAppLogin {
        // version 1
        public static final ServiceSign autoLogin = new ServiceSign("TAppLogin.autoLogin");
        public static final ServiceSign Check = new ServiceSign("TAppLogin.Check");
        public static final ServiceSign ExitSystem = new ServiceSign("TAppLogin.ExitSystem");
        public static final ServiceSign getState = new ServiceSign("TAppLogin.getState");
        public static final ServiceSign getTelToUserCode = new ServiceSign("TAppLogin.getTelToUserCode");
        public static final ServiceSign sendVerifyCode = new ServiceSign("TAppLogin.sendVerifyCode");
        public static final ServiceSign verifyMachine = new ServiceSign("TAppLogin.verifyMachine");
    }

    public static class TAppTaskVineLink {
        public static final ServiceSign generalizePart = new ServiceSign("TAppTaskVineLink.generalizePart")
                .setVersion(0);
        public static final ServiceSign resetSync = new ServiceSign("TAppTaskVineLink.resetSync").setVersion(0);
        public static final ServiceSign syncCWCode = new ServiceSign("TAppTaskVineLink.syncCWCode").setVersion(0);
    }

    public static class TAppTaskMessage {
        public static final ServiceSign updateStatus = new ServiceSign("TAppTaskMessage.updateStatus");
    }

    public static class SvrAreaInfo {
        /** 保存钓鱼人口比例 */
        public static final ServiceSign saveFishScale = new ServiceSign("SvrAreaInfo.saveFishScale");
        /** 获取县钓鱼人口与常住人口 */
        public static final ServiceSign downloadDetail = new ServiceSign("SvrAreaInfo.downloadDetail")
                .setProperties(Set.of("Area1_", "Area2_", "Area3_"));
        /** 保存县钓鱼人口与常住人口 */
        public static final ServiceSign savePopulation = new ServiceSign("SvrAreaInfo.savePopulation")
                .setProperties(Set.of("Area1_", "Area2_", "Area3_"));
        /** excel批次导入县区常住人口 */
        public static final ServiceSign importExcel = new ServiceSign("SvrAreaInfo.importExcel");
    }

    public static void main(String[] args) {
        ServiceSign.buildSourceCode(TAppUserInfo.class);
    }
}
