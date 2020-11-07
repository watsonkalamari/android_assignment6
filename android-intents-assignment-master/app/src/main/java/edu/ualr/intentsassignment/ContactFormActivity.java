package edu.ualr.intentsassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import edu.ualr.intentsassignment.databinding.ContactFormActivityBinding;
import edu.ualr.intentsassignment.model.Contact;

public class ContactFormActivity extends AppCompatActivity {
    // TODO 01. Create a new layout file to define the GUI elements of the ContactFormActivity.
    // TODO 02. Define the basic skeleton of the ContactFormActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    // TODO 06. Create a new method that reads the values in the several EditText elements of the layout and then uses the Contact class to send those data to ContactInfoActivity

    private static final String TAG = ContactFormActivity.class.getSimpleName();
    private ContactFormActivityBinding binding;

    public void newPerson (){
        Contact person = new Contact();
        person.setFirstName(binding.fNameEd.getText().toString());
        person.setLastName(binding.lNameEd.getText().toString());
        person.setPhoneNumber(binding.phoneEd.getText().toString());
        person.setEmailAddress(binding.emailEd.getText().toString());
        person.setAddress(binding.addressEd.getText().toString());
        person.setWebsite(binding.websiteEd.getText().toString());
    }

   /* @Override*/
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ContactFormActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.saveContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPerson();
                Intent intent = new Intent (ContactFormActivity.this, ContactInfoActivity.class);
                startActivity(intent);
            }
        });

    }



}
