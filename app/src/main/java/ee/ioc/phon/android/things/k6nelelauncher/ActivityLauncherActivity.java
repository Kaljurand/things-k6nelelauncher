package ee.ioc.phon.android.things.k6nelelauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.GpioCallback;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;

/**
 * This activity launches the K6nele voice search panel with the trigger service.
 * This happens automatically (after reboot)
 * or after pressing a button on a basic and optional UI (GPIO button + on-screen button).
 * After launching K6nele, this activity never resumes.
 * This means e.g. that we cannot blink the GPIO lights in response to speech activity.
 * Once the trigger is heard,
 * the K6nele panel relaunches itself with a given rewrite table (currently fixed to "Hue").
 */
public class ActivityLauncherActivity extends Activity {

    private static final boolean DEFAULT_MODE_TRIGGER = true;
    private static final boolean DEFAULT_AUTO_START = true;
    private static final String DEFAULT_GPIO_BUTTON = "BCM4";

    // Keyword spotting service
    private static final String DEFAULT_SERVICE1 = "ee.ioc.phon.android.speechtrigger/.TriggerService";
    // Full LM service
    private static final String DEFAULT_SERVICE2 = "ee.ioc.phon.android.speak/.service.WebSocketRecognitionService";

    private static final String DEFAULT_PHRASE = "hey wake up";
    private static final String DEFAULT_PROMPT1 = "Say " + DEFAULT_PHRASE;
    private static final String DEFAULT_PROMPT2 = "Öelge näiteks pane elutoa tuli põlema/kustu.";

    private static final String DEFAULT_LOCALE1 = "en-US";

    private static final String DEFAULT_LOCALE2 = "et-EE";

    private static final String DEFAULT_REWRITES_TABLE2 = "Hue";

    private Gpio mButtonGpio;

    private GpioCallback mCallback = new GpioCallback() {
        @Override
        public boolean onGpioEdge(Gpio gpio) {
            Log.i("GPIO changed, button pressed");

            launchK6nele();

            // Return true to keep callback active.
            // Return true within onGpioEdge() to continue receiving future edge trigger events.
            return true;
        }
    };

    private boolean mK6neleIsRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bLaunchK6nele).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchK6nele();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mK6neleIsRunning = false;
        Bundle extras = getExtras();
        PeripheralManagerService service = new PeripheralManagerService();
        try {
            // Create GPIO connection.
            // Use PeripheralManagerService to open a connection with the GPIO port wired to the button.
            mButtonGpio = service.openGpio(extras.getString("gpio_button", DEFAULT_GPIO_BUTTON));

            // Configure as an input.
            // Configure the port with DIRECTION_IN.
            mButtonGpio.setDirection(Gpio.DIRECTION_IN);

            // Enable edge trigger events.
            // Configure which state transitions will generate callback events with setEdgeTriggerType().
            mButtonGpio.setEdgeTriggerType(Gpio.EDGE_FALLING);

            // Register an event callback.
            // Register a GpioCallback to receive edge trigger events.
            mButtonGpio.registerGpioCallback(mCallback);
        } catch (IOException e) {
            Log.e("Error while setting up PeripheralIO API", e);
        }

        maybeLaunchK6nele(getIntent());
    }

    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        maybeLaunchK6nele(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Close the resource
        // When the application no longer needs the GPIO connection, close the Gpio resource.
        if (mButtonGpio != null) {
            mButtonGpio.unregisterGpioCallback(mCallback);
            try {
                mButtonGpio.close();
            } catch (IOException e) {
                Log.e("Error while closing PeripheralIO API", e);
            }
        }
    }

    private Bundle getExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        return extras;
    }

    private void maybeLaunchK6nele(Intent intent) {
        if (intent.getBooleanExtra("auto_start", DEFAULT_AUTO_START)) {
            launchK6nele();
        }
    }

    private void launchK6nele() {
        if (!mK6neleIsRunning) {
            mK6neleIsRunning = true;
            Log.i("Launching Kõnele");
            if (DEFAULT_MODE_TRIGGER) {
                startActivity(Utils.createRecognizerIntent(
                        DEFAULT_LOCALE1, DEFAULT_LOCALE2,
                        DEFAULT_PHRASE,
                        DEFAULT_PROMPT1, DEFAULT_PROMPT2,
                        DEFAULT_SERVICE1, DEFAULT_SERVICE2,
                        DEFAULT_REWRITES_TABLE2));
            } else {
                startActivity(Utils.createK6neleIntent(
                        DEFAULT_LOCALE2,
                        DEFAULT_PROMPT2,
                        DEFAULT_SERVICE2,
                        DEFAULT_REWRITES_TABLE2));
            }
        }
    }
}