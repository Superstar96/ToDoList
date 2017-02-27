package com.star.todolist;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by SUPERSTAR on 24.02.2017.
 */

public class FileOperations {
    private static final String FILE_NAME = "Missions.txt";
    private Context m_context;

    public FileOperations(Context context)
    {
        m_context = context;
    }



    public void createFile()
    {
        File file = new File(m_context.getFilesDir(), FILE_NAME);

        if (!file.exists()) {
           try {
               if(file.createNewFile())
                   this.displayMessages("File Created", 2);
               else
                   this.displayMessages("File Didn't Created", 2);
           }
           catch (Exception ex) {
               this.displayMessages("CreateFile: " + ex.getMessage(), 2);
           }
        }
        else
            this.displayMessages("File Already Exist", 2);
    }

    public void displayMessages(String message, int request)
    {
        if(request == 1)
            Toast.makeText(m_context, message, Toast.LENGTH_LONG).show();
        else
            Log.e("ERROR", message);
    }

     public boolean saveMissions(ArrayList<Mission> missions)
    {
        boolean result = true;
        try (FileOutputStream fos = m_context.openFileOutput(FILE_NAME, Context.MODE_APPEND)) {

            for (int i = 0; i < missions.size(); i++) {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(missions.get(i));
            }
        }
        catch (FileNotFoundException ex) {
            result = false;
            this.displayMessages("FileNotFound Exception " + ex.getMessage(), 2);
        }
        catch (IOException ex) {
            result = false;
            this.displayMessages("IO Exception " + ex.getMessage(), 2);
        }
        catch (Exception ex) {
            result = false;
            this.displayMessages("Exception " + ex.getMessage(), 2);
        }

        return result;
    }

    public ArrayList<Mission> getAllMissions ()
    {
        int a = 0;
        ArrayList<Mission> list = new ArrayList<>();

        try (FileInputStream fis = m_context.openFileInput(FILE_NAME)) {

            for (;;) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                //this.displayMessages("" + a++);
                Mission mission = (Mission) ois.readObject();
                list.add(mission);
            }
        }
        catch (EOFException ex) {
            this.displayMessages("EOF Exception " + ex.getMessage(), 2);
        }
        catch (IOException ex) {
            this.displayMessages("IO Exception " + ex.getMessage(), 2);
        }
        catch (Exception ex) {
            this.displayMessages("Exception " + ex.getMessage(), 2);
        }

        return list;
    }
}
