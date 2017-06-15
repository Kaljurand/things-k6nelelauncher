package ee.ioc.phon.android.things.k6nelelauncher;

import android.content.ComponentName;
import android.content.Intent;
import android.speech.RecognizerIntent;

public final class Utils {

    private static final String ACTIVITY = "ee.ioc.phon.android.speak/.activity.SpeechActionActivity";

    private Utils() {
    }

    public static Intent createRecognizerIntent(String locale1, String locale2,
                                                String phrase,
                                                String prompt1, String prompt2,
                                                String service1, String service2,
                                                String resultRewrites2) {
        Intent intent = new Intent(RecognizerIntent.ACTION_WEB_SEARCH);
        intent.setComponent(ComponentName.unflattenFromString(ACTIVITY));
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        // TODO: VOICE_PROMPT has issues on Android Things
        //intent.putExtra(Extras.EXTRA_VOICE_PROMPT, prompt1);
        intent.putExtra(Extras.EXTRA_SERVICE_COMPONENT, service1);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, prompt1);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, locale1);
        intent.putExtra(Extras.EXTRA_AUTO_START, true);
        intent.putExtra(Extras.EXTRA_AUDIO_CUES, true);
        intent.putExtra(Extras.EXTRA_RETURN_ERRORS, true);
        intent.putExtra(Extras.EXTRA_FINISH_AFTER_LAUNCH_INTENT, false);
        intent.putExtra(Extras.EXTRA_PHRASE, phrase);
        intent.putExtra(Extras.EXTRA_RESULT_UTTERANCE, ".+");
        intent.putExtra(Extras.EXTRA_RESULT_COMMAND, "activity");
        // TODO: create this from JSON
        // TODO: VOICE_PROMPT has issues on Android Things
        // "\"ee.ioc.phon.android.extra.VOICE_PROMPT\":\"" + prompt2 + "\"," +
        intent.putExtra(Extras.EXTRA_RESULT_ARG1,
                "{\"component\": \"" + ACTIVITY + "\"," +
                        "\"extras\":{" +
                        "\"ee.ioc.phon.android.extra.SERVICE_COMPONENT\":\"" + service2 + "\"," +
                        "\"android.speech.extra.PROMPT\":\"" + prompt2 + "\"," +
                        "\"android.speech.extra.MAX_RESULTS\":1," +
                        "\"android.speech.extra.LANGUAGE\":\"" + locale2 + "\"," +
                        "\"ee.ioc.phon.android.extra.AUTO_START\": True," +
                        "\"ee.ioc.phon.android.extra.AUDIO_CUES\": True," +
                        "\"ee.ioc.phon.android.extra.FINISH_AFTER_LAUNCH_INTENT\": True," +
                        "\"ee.ioc.phon.android.extra.RETURN_ERRORS\": True," +
                        "\"ee.ioc.phon.android.extra.RESULT_REWRITES\": [\"" + resultRewrites2 + "\"]}}");
        return intent;
    }

    /**
     * Just for testing.
     */
    public static Intent createK6neleIntent(String locale, String prompt, String service, String resultRewrites) {
        Intent intent = new Intent(RecognizerIntent.ACTION_WEB_SEARCH);
        intent.setComponent(ComponentName.unflattenFromString(ACTIVITY));
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        //intent.putExtra(Extras.EXTRA_VOICE_PROMPT, prompt);
        intent.putExtra(Extras.EXTRA_SERVICE_COMPONENT, service);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, prompt);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, locale);
        intent.putExtra(Extras.EXTRA_AUTO_START, true);
        intent.putExtra(Extras.EXTRA_AUDIO_CUES, true);
        intent.putExtra(Extras.EXTRA_RETURN_ERRORS, true);
        intent.putExtra(Extras.EXTRA_FINISH_AFTER_LAUNCH_INTENT, true);
        intent.putExtra(Extras.EXTRA_RESULT_REWRITES, resultRewrites);
        return intent;
    }
}