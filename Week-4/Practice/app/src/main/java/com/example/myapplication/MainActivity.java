package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;



public class MainActivity extends AppCompatActivity {

    String filename = "nighttt.jpeg";
    public MainActivity() throws FileNotFoundException {
    }

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = (TextView) findViewById(R.id.title);
        registerForContextMenu(title);

        SharedPreferences settings = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("silentMode", false);
        editor.commit();

        Button silentButton = (Button) findViewById(R.id.silent_button);
        TextView silentText = (TextView) findViewById(R.id.silent_text);
        View.OnClickListener silentButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean silented = settings.getBoolean("silentMode", false);

                editor.putBoolean("silentMode", !silented);

                editor.commit();
            }
        };

        silentButton.setOnClickListener(silentButtonListener);


        @SuppressLint("SetTextI18n") SharedPreferences.OnSharedPreferenceChangeListener listener = (sharedPreferences, key) -> {
            Boolean tam = sharedPreferences.getBoolean("silentMode", false);
            if (tam) {
                silentText.setText("False");
            } else {
                silentText.setText("True");
            }
        };
        settings.registerOnSharedPreferenceChangeListener(listener);

        try {
            updateFileInput();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Button writeFileInputButton = (Button) findViewById(R.id.write_file_button);
        Button updateFileInputButton = (Button) findViewById(R.id.update_file_button);
        View.OnClickListener writeFileInputButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    writeFileInput();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        writeFileInputButton.setOnClickListener(writeFileInputButtonListener);

        View.OnClickListener updateFileInputButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    updateFileInput();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        updateFileInputButton.setOnClickListener(updateFileInputButtonListener);

        TextView readable = (TextView) findViewById(R.id.readable);
        TextView writable = (TextView) findViewById(R.id.writable);
        if (isExternalStorageReadable()) {
            readable.setText("Readable");
        } else {
            readable.setText("Unreadable");
        }
        if (isExternalStorageWritable()) {
            writable.setText("Writable");
        } else {
            writable.setText("Unwritable");
        }

        try {
            updateAlbumStorage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Button uploadImageButton = (Button) findViewById(R.id.upload_image_button);
        View.OnClickListener uploadImageButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    updateAlbumStorage();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                String fileName = "MyPictures/" + filename;
                File imageFile = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES), fileName);
                ImageView imageView = (ImageView) findViewById(R.id.image_view);
                if (imageFile.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    } else {
                        Log.e("Error", "Failed to decode bitmap");
                    }
                } else {
                    Log.e("Error", "Image not found");
                }
            }
        };
        uploadImageButton.setOnClickListener(uploadImageButtonListener);

        Button deleteImageButton = (Button) findViewById(R.id.delete_image_button);
        View.OnClickListener deleteImageButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteExternalStoragePrivateFile();
                ImageView imageView = (ImageView) findViewById(R.id.image_view);
                imageView.setImageBitmap(null);
            }
        };
        deleteImageButton.setOnClickListener(deleteImageButtonListener);

        Button insertButton = (Button) findViewById(R.id.insert_sqLite_button);
        View.OnClickListener insertButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertSqLite();
            }
        };
        insertButton.setOnClickListener(insertButtonListener);

        Button deleteButton = (Button) findViewById(R.id.delete_sqLite_button);
        View.OnClickListener deleteButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSqLite();
            }
        };
        deleteButton.setOnClickListener(deleteButtonListener);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 2131231208:
                startActivity(new Intent(this, MainActivity2.class));
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    void writeFileInput() throws Exception {
        String FILENAME = "hello.txt";
        String string = "Hello World1111231211";
        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        fos.write(string.getBytes());
        fos.close();
    }
    void updateFileInput() throws Exception {
        try {
            File file = this.getDir("hello.txt", Context.MODE_PRIVATE);
            if (file.exists()) {
                FileInputStream fis = this.openFileInput("hello.txt");
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                TextView inputFileText = (TextView) findViewById(R.id.input_file_text);
                inputFileText.setText(sb.toString());
                fis.close();
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public Boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public void updateAlbumStorage() throws IOException {
        Drawable drawable = getDrawable(R.drawable.night);

        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        File outputDirectory = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyPictures");
        if (!outputDirectory.exists()) {
            if (!outputDirectory.mkdirs()) {
                Log.e("Error", "Directory not created");
            }
        }
        File outputImage = new File(outputDirectory, filename);
        try {
            FileOutputStream outputStream = new FileOutputStream(outputImage);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();
            Log.d("Success", "Image saved to " + outputImage.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error", "Error saving image");
        }
    }

    void deleteExternalStoragePrivateFile() {
        String fileName = "MyPictures/" + filename;
        File imageFile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), fileName);
        if (imageFile != null) {
            imageFile.delete();
        }
    }

    void readSqLite() {
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this, "my_database.db", null, 3);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String[] projection = {FeedReaderContract.FeedEntry._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE};
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "1" };
        String sortOrder = FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        String res = "";
        if (cursor != null) {

            if (cursor.moveToFirst()) {
                do {
                    Long id = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
                    String subtitle = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE));
                    if (res == "") {
                        res = res + id + " " + title + " " + subtitle;
                    } else {
                        res = res + "\n" + id + " " + title + " " + subtitle;
                    }

                } while (cursor.moveToNext());
            } else {
                Log.d("sqLite", "Error");
            }
            cursor.close();
        } else {
            Log.d("sqLite", "Error");
        }
        TextView sqLiteText = (TextView) findViewById(R.id.sqLite_text);
        sqLiteText.setText(res);
    }

    void insertSqLite() {
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this, "my_database.db", null, 3);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "1");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "23456");
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);

        readSqLite();
    }

    void deleteSqLite() {
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this, "my_database.db", null, 3);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { "1" };
        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
        readSqLite();
    }
}