package com.example.lei;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * mySQL  注册的数据库  及其表名
 * @author Administrator
 *
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	

	public MySQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String create_table = "CREATE TABLE Users (userName VARCHAR(50) PRIMARY KEY, password VARCHAR(20))";
		db.execSQL(create_table);//�������ݿ��
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
