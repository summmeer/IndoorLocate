package com.sansa.locat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySQLiteOpenHelper  extends SQLiteOpenHelper {

    private static Integer Version = 1;  //Version Number

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                              int version) {
        super(context, name, factory, version);
    }
    //context:上下文对象
    //name:name of database
    //param:factory
    //version:now_version_number

    public MySQLiteOpenHelper(Context context,String name,int version)
    {
        this(context,name,null,version);
    }


    public MySQLiteOpenHelper(Context context,String name)
    {
        this(context, name, Version);
    }

    //当数据库创建的时候被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建了数据库并创建一个叫records的表
        //SQLite数据创建支持的数据类型： 整型数据，字符串类型，日期类型，二进制的数据类型
        String sql = "create table user(id int primary key,x int, y int ,LEVEL1  varchar(6),LEVEL2  varchar(6),LEVEL3  varchar(6),LEVEL4  varchar(6),LEVEL5  varchar(6),LEVEL6  varchar(6),LEVEL7  varchar(6),LEVEL8  varchar(6),LEVEL9  varchar(6),LEVEL10  varchar(6),LEVEL11  varchar(6),LEVEL12  varchar(6),LEVEL13  varchar(6),LEVEL14  varchar(6),LEVEL15  varchar(6),LEVEL16  varchar(6),LEVEL17  varchar(6),LEVEL18  varchar(6),LEVEL19  varchar(6),LEVEL20  varchar(6),LEVEL21  varchar(6),LEVEL22  varchar(6),LEVEL23  varchar(6),LEVEL24  varchar(6),LEVEL25  varchar(6),LEVEL26  varchar(6),LEVEL27  varchar(6),LEVEL28  varchar(6),LEVEL29  varchar(6),LEVEL30  varchar(6),LEVEL31  varchar(6),LEVEL32  varchar(6),LEVEL33  varchar(6),LEVEL34  varchar(6),LEVEL35  varchar(6),LEVEL36  varchar(6),LEVEL37  varchar(6),LEVEL38  varchar(6),LEVEL39  varchar(6),LEVEL40  varchar(6),LEVEL41  varchar(6),LEVEL42  varchar(6),LEVEL43  varchar(6),LEVEL44  varchar(6),LEVEL45  varchar(6),LEVEL46  varchar(6),LEVEL47  varchar(6),LEVEL48  varchar(6),LEVEL49  varchar(6),LEVEL50  varchar(6),LEVEL51  varchar(6),LEVEL52  varchar(6),LEVEL53  varchar(6),LEVEL54  varchar(6),LEVEL55  varchar(6),LEVEL56  varchar(6),LEVEL57  varchar(6),LEVEL58  varchar(6),LEVEL59  varchar(6),LEVEL60  varchar(6),LEVEL61  varchar(6),LEVEL62  varchar(6),LEVEL63  varchar(6),LEVEL64  varchar(6),LEVEL65  varchar(6),LEVEL66  varchar(6),LEVEL67  varchar(6),LEVEL68  varchar(6),LEVEL69  varchar(6),LEVEL70  varchar(6),LEVEL71  varchar(6),LEVEL72  varchar(6),LEVEL73  varchar(6),LEVEL74  varchar(6),LEVEL75  varchar(6),LEVEL76  varchar(6),LEVEL77  varchar(6),LEVEL78  varchar(6),LEVEL79  varchar(6),LEVEL80  varchar(6),LEVEL81  varchar(6),LEVEL82  varchar(6),LEVEL83  varchar(6),LEVEL84  varchar(6),LEVEL85  varchar(6),LEVEL86  varchar(6),LEVEL87  varchar(6),LEVEL88  varchar(6),LEVEL89  varchar(6),LEVEL90  varchar(6),LEVEL91  varchar(6),LEVEL92  varchar(6),LEVEL93  varchar(6),LEVEL94  varchar(6),LEVEL95  varchar(6),LEVEL96  varchar(6),LEVEL97  varchar(6),LEVEL98  varchar(6),LEVEL99  varchar(6),LEVEL100  varchar(6),LEVEL101  varchar(6),LEVEL102  varchar(6),LEVEL103  varchar(6),LEVEL104  varchar(6),LEVEL105  varchar(6),LEVEL106  varchar(6),LEVEL107  varchar(6),LEVEL108  varchar(6),LEVEL109  varchar(6),LEVEL110  varchar(6),LEVEL111  varchar(6),LEVEL112  varchar(6),LEVEL113  varchar(6),LEVEL114  varchar(6),LEVEL115  varchar(6),LEVEL116  varchar(6),LEVEL117  varchar(6),LEVEL118  varchar(6),LEVEL119  varchar(6),LEVEL120  varchar(6) )";
        db.execSQL(sql);

    }

    //数据库升级时调用
    //如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade（）方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("更新数据库版本为:"+newVersion);
    }
}
