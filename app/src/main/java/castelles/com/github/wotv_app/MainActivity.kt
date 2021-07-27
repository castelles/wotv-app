package castelles.com.github.wotv_app

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.children
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import castelles.com.github.wotv_app.databinding.ActivityMainBinding
import com.google.android.material.internal.NavigationMenuItemView
import com.google.android.material.navigation.NavigationView

class MainActivity :
    AppCompatActivity(R.layout.activity_main)
{
    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onResume() {
        super.onResume()

        setToolbar()
        setNavController()
        val drawerLayout: DrawerLayout? = setAppBarConfiguration()
        setNavigationView(drawerLayout)
    }

    private fun setNavController() {
        navController = (supportFragmentManager
            .findFragmentById(R.id.nav_host_main) as NavHostFragment).navController
    }

    private fun setNavigationView(drawerLayout: DrawerLayout?) {
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener {
            drawerLayout?.closeDrawer(GravityCompat.START)
            it.onNavDestinationSelected(navController)
            true
        }
        navigationView.setupWithNavController(navController)
    }

    private fun setAppBarConfiguration(): DrawerLayout? {
        val drawerLayout: DrawerLayout? = findViewById(R.id.dwl_root)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home,
                R.id.stats,
                R.id.builds,
                R.id.ranks,
                R.id.chars,
                R.id.espers,
                R.id.vision_cards,
                R.id.equipments
            ),
            drawerLayout
        )
        return drawerLayout
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val retValue = super.onCreateOptionsMenu(menu)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        if (navigationView == null) {
            menuInflater.inflate(R.menu.menu_drawer, menu)
            return true
        }
        return retValue
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}