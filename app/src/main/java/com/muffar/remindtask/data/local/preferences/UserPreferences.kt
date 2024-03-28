package com.muffar.remindtask.data.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.muffar.remindtask.domain.model.HeaderType
import com.muffar.remindtask.domain.model.HeaderType.Companion.toHeaderType
import com.muffar.remindtask.domain.model.HeaderType.Companion.toValue
import com.muffar.remindtask.domain.model.NotesType
import com.muffar.remindtask.domain.model.NotesType.Companion.toNotesType
import com.muffar.remindtask.domain.model.NotesType.Companion.toValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    suspend fun saveHeaderType(headerType: HeaderType) {
        dataStore.edit { preferences ->
            preferences[HEADER_TYPE] = headerType.toValue()
        }
    }

    val headerType: Flow<HeaderType>
        get() = dataStore.data.map { preferences ->
            preferences[HEADER_TYPE]?.toHeaderType() ?: HeaderType.CALENDAR
        }

    suspend fun saveNotesType(notesType: NotesType) {
        dataStore.edit { preferences ->
            preferences[NOTES_TYPE] = notesType.toValue()
        }
    }

    val notesType: Flow<NotesType>
        get() = dataStore.data.map { preferences ->
            preferences[NOTES_TYPE]?.toNotesType() ?: NotesType.LIST
        }

    companion object {
        private val HEADER_TYPE = stringPreferencesKey("header_type")
        private val NOTES_TYPE = stringPreferencesKey("notes_type")
    }
}