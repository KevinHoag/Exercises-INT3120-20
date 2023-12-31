package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = findViewById(R.id.add_button);
        View.OnClickListener onAddButtonClicked = v -> onClickAddDetails(v);
        addButton.setOnClickListener(onAddButtonClicked);

        Button addManyButton = findViewById(R.id.add_many_button);
        View.OnClickListener onAddManyButtonClicked = v -> onClickAddManyDetails(v);
        addManyButton.setOnClickListener(onAddManyButtonClicked);

        Button showButton = findViewById(R.id.show_button);
        View.OnClickListener onShowButtonClicked = v -> onClickShowDetails(v);
        showButton.setOnClickListener(onShowButtonClicked);

        Button searchButton = findViewById(R.id.search_button);
        View.OnClickListener onSearchButtonClicked = v -> onClickSearchDetails(v);
        searchButton.setOnClickListener(onSearchButtonClicked);

        Button updateButton = findViewById(R.id.update_button);
        View.OnClickListener onUpdateButtonClicked = v -> onClickUpdateDetails(v);
        updateButton.setOnClickListener(onUpdateButtonClicked);

        Button updateManyButton = findViewById(R.id.update_many_button);
        View.OnClickListener onUpdateManyButtonClicked = v -> onClickUpdateManyDetails(v);
        updateManyButton.setOnClickListener(onUpdateManyButtonClicked);

        Button deleteButton = findViewById(R.id.delete_button);
        View.OnClickListener onDeleteButtonClicked = v -> onClickDeleteDetails(v);
        deleteButton.setOnClickListener(onDeleteButtonClicked);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 2131230949:
                break;
            case 2131230950:
                Intent intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
        }
        return true;
    }



    public void onClickAddDetails(View view) {
        ContentValues values = new ContentValues();
        String tam = ((EditText) findViewById(R.id.first_edit_Text)).getText().toString();
        if (tam != "") {
            values.put(MyContentProvider.name, ((EditText) findViewById(R.id.first_edit_Text)).getText().toString());
            getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
            Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickAddManyDetails(View view) {
        ContentValues[] values = new ContentValues[]{};
        String tam = ((EditText) findViewById(R.id.first_edit_Text)).getText().toString();
        if (tam != "") {
            for (int i = 0; i < 10; i++) {
                values[i].put(MyContentProvider.name, ((EditText) findViewById(R.id.first_edit_Text)).getText().toString());
            }
            getContentResolver().bulkInsert(MyContentProvider.CONTENT_URI, values);
            Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
        }

    }

    @SuppressLint("Range")
    public void onClickSearchDetails(View view) {
        String searchId = ((EditText) findViewById(R.id.first_edit_Text)).getText().toString();
        if (!searchId.isEmpty()) {
            TextView resultView= findViewById(R.id.text_show);
            String mProjection[] = {
                    MyContentProvider.id,
                    MyContentProvider.name,
            };
            String mSelection = MyContentProvider.name + " like ?";
            String mSelectionArgs[] = {searchId};
            Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.user.provider/users"), mProjection, mSelection, mSelectionArgs, null);
            if(cursor.moveToFirst()) {
                StringBuilder strBuild=new StringBuilder();
                while (!cursor.isAfterLast()) {
                    strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+ cursor.getString(cursor.getColumnIndex("name")));
                    cursor.moveToNext();
                }
                resultView.setText(strBuild);
            }
            else {
                resultView.setText("No Records Found");
            }
        }
    }

    @SuppressLint("Range")
    public void onClickUpdateManyDetails(View view) {
        String searchId = ((EditText) findViewById(R.id.first_edit_Text)).getText().toString();
        String[] ids = new String[100];
        int dem = 0;
        if (!searchId.isEmpty()) {
            TextView resultView= findViewById(R.id.text_show);
            String mProjection[] = {
                    MyContentProvider.id,
                    MyContentProvider.name,
            };
            String mSelection = MyContentProvider.name + " like ?";
            String mSelectionArgs[] = {searchId};
            Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.user.provider/users"), mProjection, mSelection, mSelectionArgs, null);
            if(cursor.moveToFirst()) {
                StringBuilder strBuild=new StringBuilder();
                while (!cursor.isAfterLast()) {
                    strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+ cursor.getString(cursor.getColumnIndex("name")));
                    ids[dem] = cursor.getString(cursor.getColumnIndex("id"));
                    dem++;
                    cursor.moveToNext();
                }
                resultView.setText(strBuild);
            }
            else {
                resultView.setText("No Records Found");
            }
        }
        int updatesRecord = 0;
        for (int i = 0; i < dem; i++) {
            Log.d("tam", ids[i].toString());
            Uri uriToUpdate = Uri.withAppendedPath(MyContentProvider.CONTENT_URI, ids[i].toString());
            ContentValues values = new ContentValues();
            String tam = "Phu" + i;
            values.put(MyContentProvider.name, tam);
            String where = MyContentProvider.id + "=?";
            String[] selectionArgs = new String[]{ids[i].toString()};
            int rowsUpdated = getContentResolver().update(uriToUpdate, values, where, selectionArgs);
            if (rowsUpdated > 0) {
                updatesRecord++;
                Toast.makeText(getBaseContext(), "Record Updated", Toast.LENGTH_LONG).show();
            } else {
            }
        }
        if (updatesRecord == 0) {
            Toast.makeText(getBaseContext(), "No Record Updated", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("Range")
    public void onClickShowDetails(View view) {
        TextView resultView= findViewById(R.id.text_show);
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.user.provider/users"), null, null, null, null);
        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+ cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
            resultView.setText(strBuild);
        }
        else {
            resultView.setText("No Records Found");
        }
    }

    public void onClickDeleteDetails(View view) {
        String deleteId = ((EditText) findViewById(R.id.first_edit_Text)).getText().toString();
        if (!deleteId.isEmpty()) {
            Uri uriToDelete = Uri.withAppendedPath(MyContentProvider.CONTENT_URI, deleteId);
            String selection = MyContentProvider.name + "=?";
            String[] selectionArgs = new String[]{deleteId};
            int rowsDeleted = getContentResolver().delete(uriToDelete, selection, selectionArgs);
            if (rowsDeleted > 0) {
                Toast.makeText(getBaseContext(), "Record Deleted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getBaseContext(), "No Record Deleted", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getBaseContext(), "Please enter an ID to delete", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickUpdateDetails(View view) {
        String updateId = ((EditText) findViewById(R.id.first_edit_Text)).getText().toString();
        String newValue = ((EditText) findViewById(R.id.second_edit_Text)).getText().toString();
        
        if (!updateId.isEmpty() && !newValue.isEmpty()) {
            Uri uriToUpdate = Uri.withAppendedPath(MyContentProvider.CONTENT_URI, updateId);
            ContentValues values = new ContentValues();
            values.put(MyContentProvider.name, newValue);
            String where = MyContentProvider.name + "=?";
            String[] selectionArgs = new String[]{updateId};
            int rowsUpdated = getContentResolver().update(uriToUpdate, values, where, selectionArgs);
            if (rowsUpdated > 0) {
                Toast.makeText(getBaseContext(), "Record Updated", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getBaseContext(), "No Record Updated", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getBaseContext(), "Please enter an ID and a new value to update", Toast.LENGTH_LONG).show();
        }
    }
}
