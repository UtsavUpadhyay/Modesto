package com.modestoappln;

import java.util.ArrayList;

import com.DaoOBJ.Daoaccess;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MobileCon extends ListActivity {
    ListView list;
    //LinearLayout ll;
  //  Button loadBtn;
    ArrayList<String> chat=new ArrayList<String>();
    ArrayList<String> al=new ArrayList<String>();
    Daoaccess dao=new Daoaccess();
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    list = getListView();
	    chat=dao.getChatPlaces();
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        LoadContactsAyscn lca = new LoadContactsAyscn();
        lca.execute();
        list.setMultiChoiceModeListener(new ModeCallback());
        
        
    }

	class LoadContactsAyscn extends AsyncTask<Void, Void, ArrayList<String>> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

          //  pd = ProgressDialog.show(MobileCon.this, "Loading Contacts",
            //        "Please Wait");
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            // TODO Auto-generated method stub
            ArrayList<String> contacts = new ArrayList<String>();

            Cursor c = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    null, null, null);
            while (c.moveToNext()) {

                String contactName = c
                        .getString(c
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phNumber = c
                        .getString(c
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                if(phNumber.length()>10 && phNumber.length()<15)
                contacts.add(contactName + ":" + phNumber);

            }
            c.close();

            return contacts;
        }

        @Override
        protected void onPostExecute(ArrayList<String> contacts) {
            // TODO Auto-generated method stub
            super.onPostExecute(contacts);

       //     pd.cancel();

         //   ll.removeView(loadBtn);
            getActionBar().setSubtitle("Long press to start selection");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getApplicationContext(), android.R.layout.simple_list_item_activated_1, contacts);
            
            list.setAdapter(adapter);

        }

    }

    
    private class ModeCallback implements ListView.MultiChoiceModeListener {

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.list_menu, menu);
            mode.setTitle("Select Items");
            return true;
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        	Log.d("hello", "id is : "+item.getItemId());
        	switch (item.getItemId()) {
            case R.id.share:
                Toast.makeText(MobileCon.this, "Shared " + list.getCheckedItemCount() +
                        " items", Toast.LENGTH_SHORT).show();
                mode.finish();
                break;
            case R.id.submit:
            	
            	Intent in=new Intent("com.modestoappln.ChatOptions");
            	in.putStringArrayListExtra("data", chat);
            	in.putStringArrayListExtra("data1", al);
            	startActivity(in);
            	break;
            default:
            	Log.d("hello", "id is : "+item.getItemId());
                Toast.makeText(MobileCon.this, "Clicked " + item.getTitle(),
                        Toast.LENGTH_SHORT).show();
                break;
            }
            return true;
        }

        public void onDestroyActionMode(ActionMode mode) {
//        	Intent in=new Intent("com.modestoappln.ChatOptions");
//        	in.putStringArrayListExtra("data", chat);
//        	startActivity(in);
        }

        public void onItemCheckedStateChanged(ActionMode mode,
                int position, long id, boolean checked) {
            final int checkedCount = list.getCheckedItemCount();
            al.add(list.getItemAtPosition(position).toString());
            switch (checkedCount) {
                case 0:
                    mode.setSubtitle(null);
                    break;
                case 1:
                    mode.setSubtitle("One item selected");
                    break;
                default:
                    mode.setSubtitle("" + checkedCount + " items selected");
                    break;
            }
        }

    }
}