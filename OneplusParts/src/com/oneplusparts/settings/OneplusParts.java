/*
 * Copyright (C) 2015 The CyanogenMod Project
 *               2017-2020 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oneplusparts.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

import com.oneplusparts.settings.doze.DozeSettingsActivity;
import com.oneplusparts.settings.preference.VibratorStrengthPreference;

public class OneplusParts extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String KEY_CATEGORY_VIBRATOR = "vibrator";

    public static final String KEY_VIBSTRENGTH = "vib_strength";
    public static final String KEY_SETTINGS_PREFIX = "device_setting_";


    private VibratorStrengthPreference mVibratorStrength;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        setPreferencesFromResource(R.xml.oneplusparts, rootKey);
        final PreferenceScreen prefScreen = getPreferenceScreen();

        Preference mDozePref = findPreference("doze");
        mDozePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(getContext(), DozeSettingsActivity.class);
                startActivity(intent);
                return true;
            }
        });

        final PreferenceCategory vibCategory =
                (PreferenceCategory) prefScreen.findPreference(KEY_CATEGORY_VIBRATOR);
        mVibratorStrength = (VibratorStrengthPreference) findPreference(KEY_VIBSTRENGTH);
        if (!VibratorStrengthPreference.isSupported()) {
            prefScreen.removePreference(vibCategory);
        }
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
    return true;
    }       


}
