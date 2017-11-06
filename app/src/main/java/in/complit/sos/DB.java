package in.complit.sos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bhaskar on 6/19/2017.
 */

public class DB extends SQLiteOpenHelper {
    String t_name="p_number";
    static String col1="Phone1",col2="Phone2",col3="Phone3",col4="Phone4",col5="Phone5";

    String e_query="create table " + t_name  +  "(" +  col1 + " varchar(12)," + col2 + " varchar(12)," +  col3 + " varchar(12),"+ col4 +" varchar(12)," + col5 + " varchar(12));";
    public DB(Context context) {
        super(context, "mydb", null, 1);
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(e_query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    long insertphone(String s1, String s2, String s3, String s4, String s5) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(col1,s1);
        contentValues.put(col2,s2);
        contentValues.put(col3,s3);
        contentValues.put(col4,s4);
        contentValues.put(col5,s5);
        SQLiteDatabase db= getWritableDatabase();
        long sq=db.insert(t_name,null,contentValues);
        return sq;
    }

    public Cursor getdata1() {
        String[] a={col1,col2,col3,col4,col5};
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        Cursor cursor=sqLiteDatabase.query(t_name,a,null,null,null,null,null);
        return cursor;
    }

}
