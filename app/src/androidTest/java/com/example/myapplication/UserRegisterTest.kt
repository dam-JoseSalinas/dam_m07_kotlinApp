import androidx.test.core.app.ActivityScenario
import org.robolectric.RobolectricTestRunner
import androidx.test.core.app.ActivityScenario.ActivityAction
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.Database
import com.example.myapplication.R
import com.example.myapplication.UserRegister
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.content.Context
import org.junit.Assert.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
class UserRegisterTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<UserRegister> =
        ActivityScenarioRule(UserRegister::class.java)


    @Before
    fun setUp() {
        // private lateinit var scenario: ActivityScenario<UserRegister>
        context = org.robolectric.RuntimeEnvironment.application
        db = Database(context, null)
    }

    @Test
    fun TestUsurioExistente() {
        db.addUser("nuevo usuario", "nuevo", "nuevo@gmail.com", "nuevo")
        
        onView(withId(R.id.register_fullname)).perform(typeText("Nuevo Usuario"))
        onView(withId(R.id.register_username)).perform(typeText("Nuevo"))
        onView(withId(R.id.register_email)).perform(typeText("nuevo@gmail.com"))
        onView(withId(R.id.register_password)).perform(typeText("nuevo"))

        // Caso de prueba para verificar la validación de campos requeridos
        onView(withId(R.id.create_user_btn)).perform(click()) // Hacer clic sin ingresar datos
        
        onView(withId(R.id.register_username)).check(matches(hasErrorText("change")))
        
    }
    @Test
    fun TestCamposRequeridos() {
        // Caso de prueba para verificar la validación de campos requeridos
        onView(withId(R.id.create_user_btn)).perform(click()) // Hacer clic sin ingresar datos

        // Verificar que se muestren los mensajes de error en los EditText
        onView(withId(R.id.register_fullname)).check(matches(hasErrorText("required")))
        onView(withId(R.id.register_username)).check(matches(hasErrorText("required")))
        onView(withId(R.id.register_email)).check(matches(hasErrorText("required")))
        onView(withId(R.id.register_password)).check(matches(hasErrorText("required")))
    }


}
