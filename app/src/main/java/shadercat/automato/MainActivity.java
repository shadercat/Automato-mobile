package shadercat.automato;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements ActionFragment.OnFragmentInteractionListener, AccountFragment.OnFragmentInteractionListener {

    final MachinesFragment machinesFragment = new MachinesFragment();
    final CompaniesFragment companiesFragment = new CompaniesFragment();
    final ActionFragment actionFragment = new ActionFragment();
    final AccountFragment accountFragment = new AccountFragment();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = machinesFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_machines:
                    fm.beginTransaction().hide(active).show(machinesFragment).commit();
                    active = machinesFragment;
                    return true;
                case R.id.navigation_companies:
                    fm.beginTransaction().hide(active).show(companiesFragment).commit();
                    active = companiesFragment;
                    return true;
                case R.id.navigation_actions:
                    fm.beginTransaction().hide(active).show(actionFragment).commit();
                    active = actionFragment;
                    return true;
                case R.id.navigation_account:
                    fm.beginTransaction().hide(active).show(accountFragment).commit();
                    active = accountFragment;
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm.beginTransaction().add(R.id.main_frame, accountFragment, "4").hide(accountFragment).commit();
        fm.beginTransaction().add(R.id.main_frame, actionFragment, "3").hide(actionFragment).commit();
        fm.beginTransaction().add(R.id.main_frame, companiesFragment, "2").hide(companiesFragment).commit();
        fm.beginTransaction().add(R.id.main_frame, machinesFragment, "1").show(machinesFragment).commit();

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

    @Override
    public void onActionFragmentInteraction(Uri uri) {

    }

    @Override
    public void onAccountFragmentInteraction(Uri uri) {

    }
}
