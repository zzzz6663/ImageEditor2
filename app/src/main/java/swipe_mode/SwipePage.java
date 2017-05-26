package swipe_mode;

import android.Manifest;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import nm.na3r.imageeditor.R;
import permission.PermissionHandler;

public class SwipePage extends FragmentActivity {
ViewPager  viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_page_activity);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        FragmentAdapter fragmentAdapter =new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);

        new PermissionHandler().checkPermission(SwipePage.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                new PermissionHandler.OnPermissionResponse() {
            @Override
            public void onPermissionGranted() {


            }

            @Override
            public void onPermissionDenied() {

                Toast.makeText(SwipePage.this,"مجوز توسط کاربر کنسل شد..",Toast.LENGTH_LONG).show();
            }
        });

    }
}
