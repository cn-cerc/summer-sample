package cn.cerc.training.service;

import cn.cerc.core.DataSet;
import cn.cerc.core.Record;
import cn.cerc.core.Utils;
import cn.cerc.db.mysql.SqlQuery;
import cn.cerc.mis.core.CustomService;
import cn.cerc.training.common.AppDB;

public class SvrPartInfo extends CustomService{

    public boolean search() {
        Record headIn = getDataIn().getHead();
        String code = headIn.getString("Code_");
        if(code !=null && !"".equals(code)) {
            
        }
        
        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s ", AppDB.Table_Example);
        cdsTmp.add("where CorpNo_='%s'",AppDB.CorpNo);
        DataSet dataSet =   cdsTmp.open();
           
        while(dataSet.fetch()) {
            int num = dataSet.getInt("Num_");
            stock
            int value = stock -num;
            dataSet.setField("value_", value);
        }
        
        getDataOut().appendDataSet(dataSet);
        
        return true;
    }
    
}
