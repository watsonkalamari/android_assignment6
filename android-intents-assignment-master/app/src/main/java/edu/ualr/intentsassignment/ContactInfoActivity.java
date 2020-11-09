package edu.ualr.intentsassignment;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import edu.ualr.intentsassignment.databinding.ContactInfoActivityBinding;
import edu.ualr.intentsassignment.model.Contact;

public class ContactInfoActivity extends AppCompatActivity {
    // TODO 03. Create a new layout file to define the GUI elements of the ContactInfoActivity.
    // TODO 04. Define the basic skeleton of the ContactInfoActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    // TODO 07. Create a new method that reads the contact info coming from ContactFormActivity and use it to populate the several TextView elements in the layout.
    // TODO 08. Create a new method that invokes a Phone Dialer app, using as parameter the phone number included in the contact info received from ContactFormActivity in the previous step
    // TODO 09. Create a new method that invokes a Messages app, using as parameter the phone number included in the contact info received from ContactFormActivity in the 7th step
    // TODO 10. Create a new method that invokes a Maps app, using as parameter the address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 11. Create a new method that invokes an Email app, using as parameter the email address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 12. Create a new method that invokes an Web Browser app, using as parameter the web url included in the contact info received from ContactFormActivity in the 7th step

    private ContactInfoActivityBinding binding;
    private Contact contact;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ContactInfoActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        populateLayout();


        binding.callChp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumberUri = "tel:";
                phoneNumberUri = phoneNumberUri.concat(reformatPhoneNumber(contact.getPhoneNumber()));
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumberUri));
                startActivity(intent);
            }
        });
        binding.textChp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String smsUri = "smsto:";
              smsUri = smsUri.concat(reformatPhoneNumber(contact.getPhoneNumber()));
              Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(smsUri));
              intent.putExtra("sms_body","");
              startActivity(intent);
            }
        });
        binding.emailChp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailRecieverList[] = {contact.getEmailAddress()};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, emailRecieverList);
                startActivity(intent);

            }
        });
        binding.mapChp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String place = contact.getAddress();
                String placeUri = String.format("geo:0,0?q=(%s)", place);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(placeUri));
                startActivity(intent);
            }
        });
        binding.webChp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String webUri = "https://";
                webUri= webUri.concat(contact.getWebsite());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUri));
                startActivity(intent);
            }
        });
    }

    public void populateLayout() {
        contact = getIntent().getParcelableExtra(ContactFormActivity.EXTRA_CONTACT);
        binding.fullnameTv.setText(String.format(contact.getFirstName() + " " + contact.getLastName()));
        binding.phoneDispalyTv.setText(reformatPhoneNumber(contact.getPhoneNumber()));
        binding.emailDisplayTv.setText(contact.getEmailAddress());
        binding.addressDisplayTv.setText(String.format(contact.getAddress()));
        binding.websiteDisplayTv.setText(String.format(contact.getWebsite()));
    }

    public String reformatPhoneNumber(String oldFormat) {
        String newFormat = "";
        if (oldFormat.length() == 10) {
            newFormat = oldFormat.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
        }
        return newFormat;
    }
}