package com.demo.yige.fruits.tool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.tsz.afinal.FinalDb;

/**
 * Created by yige on 2016/12/22.
 */

public class DBTools {

    /**
     * 获取FinalDB对象
     *
     * @param context
     * @return
     */
    public static FinalDb getFinalDB(Context context) {
        return FinalDb.create(context, "Fruits.db", true, 1,
                new FinalDb.DbUpdateListener() {

                    @Override
                    public void onUpgrade(SQLiteDatabase arg0, int arg1,
                                          int arg2) {
                        // 版本更新时执行
                    }
                });
    }

}
