package ee.ioc.phon.android.things.k6nelelauncher;

/**
 * <p>EXTRAs for RecognizerIntent and SpeechRecognizer,
 * in addition to the standard Android EXTRAs defined as part of RecognizerIntent.</p>
 *
 * @author Kaarel Kaljurand
 */
public class Extras {

    /**
     * String.
     * Class name of the recognizer component name, e.g.
     * ee.ioc.phon.android.speak/.service.WebSocketRecognitionService
     */
    public static final String EXTRA_SERVICE_COMPONENT = "ee.ioc.phon.android.extra.SERVICE_COMPONENT";

    /**
     * String.
     * Desired transcription.
     * Using this extra, the user can specify to which string the enclosed audio should be transcribed.
     */
    public static final String EXTRA_PHRASE = "ee.ioc.phon.android.extra.PHRASE";

    /**
     * String.
     * Optional text prompt to read out to the user when asking them to speak in the RecognizerIntent activity.
     * See also "android.speech.extra.PROMPT".
     */
    public static final String EXTRA_VOICE_PROMPT = "ee.ioc.phon.android.extra.VOICE_PROMPT";

    /**
     * Boolean.
     * True iff the recognizer should play audio cues to indicate start and end of
     * recording, as well as error conditions.
     */
    public static final String EXTRA_AUDIO_CUES = "ee.ioc.phon.android.extra.AUDIO_CUES";

    /**
     * Boolean.
     * Start the recognition session immediately without the user having to press a button.
     */
    public static final String EXTRA_AUTO_START = "ee.ioc.phon.android.extra.AUTO_START";

    /**
     * Boolean. (Default: true in single window mode, false in multi-window mode)
     * True iff voice search panel will be terminated after it launched the intent.
     */
    public static final String EXTRA_FINISH_AFTER_LAUNCH_INTENT = "ee.ioc.phon.android.extra.FINISH_AFTER_LAUNCH_INTENT";

    /**
     * Boolean.
     * In case of an audio/network/etc. error, finish the RecognizerIntent activity with the error code,
     * allowing the caller to handle the error.
     * Normally errors are handled by the activity so that the activity only returns with success.
     * However, in certain situations it is useful to let the caller handle the errors. If this
     * is desired then the caller can request the returning of the errors using this EXTRA.
     */
    public static final String EXTRA_RETURN_ERRORS = "ee.ioc.phon.android.extra.RETURN_ERRORS";

    /**
     * String (must be a Java regular expression).
     * Regular expression applied to the transcription result(s).
     */
    public static final String EXTRA_RESULT_UTTERANCE = "ee.ioc.phon.android.extra.RESULT_UTTERANCE";

    /**
     * String (must be a Java regular expression replacement).
     * Replacement applied to the transcription result(s) if EXTRA_RESULT_UTTERANCE matches.
     */
    public static final String EXTRA_RESULT_REPLACEMENT = "ee.ioc.phon.android.extra.RESULT_REPLACEMENT";

    /**
     * String.
     * Name of a command.
     */
    public static final String EXTRA_RESULT_COMMAND = "ee.ioc.phon.android.extra.RESULT_COMMAND";

    /**
     * String.
     * Content of the 1st argument of the command.
     */
    public static final String EXTRA_RESULT_ARG1 = "ee.ioc.phon.android.extra.RESULT_ARG1";

    /**
     * String.
     * Content of the 2nd argument of the command.
     */
    public static final String EXTRA_RESULT_ARG2 = "ee.ioc.phon.android.extra.RESULT_ARG2";

    /**
     * Boolean.
     * If @code{true} then the following EXTRAs are set in the following way:
     * EXTRA_RESULT_UTTERANCE = "(.+)"
     * EXTRA_RESULT_COMMAND = "activity"
     * EXTRA_RESULT_ARG1 = "$1"
     */
    public static final String EXTRA_RESULT_LAUNCH_AS_ACTIVITY = "ee.ioc.phon.android.extra.RESULT_LAUNCH_AS_ACTIVITY";

    /**
     * String[].
     * List of transcription results.
     */
    public static final String EXTRA_RESULT_RESULTS = "ee.ioc.phon.android.extra.RESULT_RESULTS";

    /**
     * String[] (String can also be used to denote a single element list)
     * List of names of rewrite tables that should apply to the transcription results.
     */
    public static final String EXTRA_RESULT_REWRITES = "ee.ioc.phon.android.extra.RESULT_REWRITES";

    /**
     * String.
     * Rewrite table (in TSV-format and with a header) that should apply to the transcription results.
     */
    public static final String EXTRA_RESULT_REWRITES_AS_STR = "ee.ioc.phon.android.extra.RESULT_REWRITES_AS_STR";
}