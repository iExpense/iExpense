package io.github.iexpense;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;

/**
 * Created by Narottam on 10/10/2016.
 */

public class NavigationActivity extends Activity implements OnClickListener {
   private static final int RESULT_LOAD_IMAGE = 1;
    Button b1, uploadButton;
    ImageView iw, imageToUpload;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.nav_header_main);

        uploadButton = (Button) findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(this);

        imageToUpload = (ImageView) findViewById(R.id.imageToUpload);
        imageToUpload.setOnClickListener(this);
        uploadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageToUpload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
       startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && requestCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
        }
    }
}
