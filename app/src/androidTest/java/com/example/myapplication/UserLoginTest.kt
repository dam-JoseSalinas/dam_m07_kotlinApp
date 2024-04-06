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
import androidx.test.espresso.intent.Intents

@RunWith(AndroidJUnit4::class)
class UserLoginTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<UserLogin> =
        ActivityScenarioRule(UserLogin::class.java)

    @Before
    fun setUp() {
        // Inicializar Intents
        Intents.init()
    }

    @Test
    fun TestUsuarioCreado() {
        onView(withId(R.id.register_btn_login)).perform(click())
        intended(hasComponent(UserRegister::class.java.name))
    }

}
