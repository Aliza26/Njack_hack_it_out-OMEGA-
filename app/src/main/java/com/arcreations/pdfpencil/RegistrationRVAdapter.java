package com.arcreations.pdfpencil;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RegistrationRVAdapter extends RecyclerView.Adapter<RegistrationRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<RegistrationModal> registrationModalArrayList;
    private Context context;
    String mText;

    // constructor
    public RegistrationRVAdapter(ArrayList<RegistrationModal>registrationModalArrayList, Context context ){
        this.registrationModalArrayList = registrationModalArrayList;
        this.context = context;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.registration_rv_item, parent, false);
        return new ViewHolder(view);

    }






    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        RegistrationModal modal = registrationModalArrayList.get(position);

        holder.subTV.setText(modal.getSubject());
        //holder.messageTV.setText(modal.getSub());
        //holder.signatureTV.setText(modal.getSub());

        holder.optionsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }

            private  void showPopupMenu(View view){

                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.recycler_menu_popup);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menuDelete:
                                Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
                                DBHandler dbHandler = new DBHandler(context);
                                dbHandler.deleteRegistration(modal.getSubject());

                                notebookFragment NotebookFragment = new notebookFragment();
                                NotebookFragment.newActivity();
                                return true;

                            case R.id.menuSave:
                                //Toast.makeText(context, "saved", Toast.LENGTH_SHORT).show();
                                saveToTxtFile(mText);


                                //notebookFragment NotebookFragment2 = new notebookFragment();
                                //NotebookFragment2.saveActivity();
                                return true;

                            default:
                                return false;

                        }
                    }
                });
                popupMenu.show();
            }

        });


        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, RTEditorActivity.class);

                // below we are passing all our values.
                i.putExtra("subject", modal.getSubject());
                i.putExtra("message", modal.getMessage());
                i.putExtra("signature", modal.getSignature());


                //sub = getIntent().getStringExtra("sub");
                //message = getIntent().getStringExtra("message");
                //signature = getIntent().getStringExtra("signature");


                // starting our activity.
                context.startActivity(i);
            }
        });







    }

    private void saveToTxtFile(String mText){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(System.currentTimeMillis());

        try {
            //path tp storage
            File path = Environment.getExternalStorageDirectory();
            //create folder named "PDF Pencil"
            File dir = new File(path+ "/PDF pencil/");
            dir.mkdirs();
            //file name
            String fileName = "PDFPencil_"+ timeStamp + ".txt";

            File file = new File(dir, fileName);

            //filewriter class is used to store character in file
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.mText);
            bw.close();

            //show file name and path where file is saved
            Toast.makeText(context, fileName+"is saved to\n"+dir, Toast.LENGTH_SHORT).show();


        }
        catch (Exception e){
            //if anything goes wrong
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();


        }
    }
    @Override
    public int getItemCount() {
        return registrationModalArrayList.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView subTV, messageTV, signatureTV,optionsTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            subTV = itemView.findViewById(R.id.idTVSub);
            optionsTV = itemView.findViewById(R.id.recyclerMenu);

            //messageTV = itemView.findViewById(R.id.idTVMessage);
           //signatureTV = itemView.findViewById(R.id.idTVSign);

        }




    }







}
