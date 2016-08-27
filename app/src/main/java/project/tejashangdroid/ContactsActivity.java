package project.tejashangdroid;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ContactsActivity extends ListActivity
{
    ListView listView;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        //-4- construct cursor
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        startManagingCursor(cursor);

        final String[] Texter = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

        int[] item = {android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter listadapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, Texter, item, 0);

        setListAdapter(listadapter);

        listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                String selectPhone = ((TextView) (view.findViewById(android.R.id.text2))).getText().toString();

                Log.d("MYLOG", "onClick: " + position + "/" + id + "/" + selectPhone);

                Intent intent = new Intent(ContactsActivity.this, TextActivity.class);

                intent.putExtra("Phone", selectPhone);

                startActivity(intent);

            }
        });





    }

    //generate 3 select methods
    @Override
    public int getSelectedItemPosition()
    {
        Log.d("MYLOG", "getSelectedItemPosition");

        return super.getSelectedItemPosition();
    }

    public ContactsActivity()
    {
        super();
    }

    @Override
    public long getSelectedItemId()
    {
        Log.d("MYLOG", "getSelectedItemID");

        return super.getSelectedItemId();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        Log.d("MYLOG","onListItemClick");

        super.onListItemClick(l, v, position, id);
    }





}



