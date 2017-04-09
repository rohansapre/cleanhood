package cleanhood.ny.hack.edu.cleanhood;

import android.app.Application;
import android.os.StrictMode;

/**
 * Created by vaibhavshukla on 4/9/17.
 */

public class CleanHoodApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }
}
