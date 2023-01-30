package mz.co.uda_urdailyactivities.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class DataBaseClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "USERDATA.db";

    //// User Variables [Columns]
    public static final String
            USR_TABLE = "user",
            USR_EMAIL = "user_email",
            USR_NAME = "user_name",
            USR_PASS = "user_password";

    //// Activity Variables [Columns]
    public static final String
            ACT_TABLE = "activity",
            ACT_NAME = "activity_name",
            ACT_PERIOD_NAME = "activity_period_name",
            ACT_INIT_DATE = "activity_initial_date",
            ACT_PERIODICITY = "activity_periodicity",
            ACT_END_DATE = "activity_end_date";


    public DataBaseClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //// User (and its features) along side the Activity creation on the "application creation" for starters
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql1 = " CREATE TABLE " + USR_TABLE + " (" +
                USR_EMAIL + " TEXT PRIMARY KEY," +
                USR_NAME + " TEXT NOT NULL," +
                USR_PASS + " TEXT NOT NULL)";
        sqLiteDatabase.execSQL(sql1);

        String sql2 = " CREATE TABLE " + ACT_TABLE + " (" +
                ACT_NAME + " TEXT PRIMARY KEY," +
                ACT_PERIODICITY + " INT NOT NULL," +
                ACT_INIT_DATE + " DATE PRIMARY KEY," +
                ACT_END_DATE + " DATE NOT NULL," +
                ACT_PERIOD_NAME + " TEXT NOT NULL)";
        sqLiteDatabase.execSQL(sql2);
    }

    //// User (and his features) Deletion on the "application Upgrade" for starters
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int older, int newer) {
        String sql = " DROP TABLE IF EXISTS " + USR_TABLE;
        sqLiteDatabase.execSQL(sql);
    }

    //// User insertion on database
    public long createUser(String email, String name, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USR_EMAIL, email);
        contentValues.put(USR_NAME, name);
        contentValues.put(USR_PASS, password);
        return sqLiteDatabase.insert(USR_TABLE, null, contentValues);

        //// Simple!
    }

    //// Get user from database
    public Cursor getUser(String[] email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sql = " SELECT * FROM " + USR_TABLE + " WHERE " + USR_EMAIL + " = ? ";
        return sqLiteDatabase.rawQuery(sql, email);

        //// Simple!
    }

    //// Activity insertion on database
    public long createActivity( String name, int periodicity, Date init_date, Date end_date, String period_name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACT_NAME, name);
        contentValues.put(ACT_PERIODICITY, periodicity);
        contentValues.put(ACT_INIT_DATE, String.valueOf(init_date));
        contentValues.put(ACT_END_DATE, String.valueOf(end_date));
        contentValues.put(ACT_PERIOD_NAME, period_name);
        return sqLiteDatabase.insert(ACT_TABLE, null, contentValues);

        //// Simple!
    }

    //// Get Activity from database
    public Cursor getActivity(String[] nome) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sql = " SELECT * FROM " + ACT_TABLE + " WHERE " + ACT_NAME + " = ? ";
        return sqLiteDatabase.rawQuery(sql, nome);

        //// Simple!
    }

}