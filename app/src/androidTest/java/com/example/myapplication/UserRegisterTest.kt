import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.ActivityAction
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.R
import com.example.myapplication.UserLogin
import com.example.myapplication.UserRegister
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent


@RunWith(AndroidJUnit4::class)
class UserRegisterTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<UserRegister> =
        ActivityScenarioRule(UserRegister::class.java)



    @Test
    fun TestCamposRequeridos() {
        onView(withId(R.id.create_user_btn)).perform(click())

        // Verificar que se muestren los mensajes de error en los EditText
        onView(withId(R.id.register_fullname)).check(matches(hasErrorText("required")))
        onView(withId(R.id.register_username)).check(matches(hasErrorText("required")))
        onView(withId(R.id.register_email)).check(matches(hasErrorText("required")))
        onView(withId(R.id.register_password)).check(matches(hasErrorText("required")))
    }
}
