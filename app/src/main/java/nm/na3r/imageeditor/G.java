package nm.na3r.imageeditor;

import android.app.Application;
import android.content.Context;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Na3r on 4/1/2017.
 */

public class G extends Application {
    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("Sahel.ttf")
                .build()
        );

        G.context =getApplicationContext();

    }
}
