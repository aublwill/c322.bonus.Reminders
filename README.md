# c322.bonus.Reminders
This project functions as a reminder app.

## Functionality 
The following **required** functionality is completed:
* [] user is promted to sign in or sign up into the app
* [] once logged in, user sees a screen with a menu at the bottom
* [] the first icon/button is to provide feedback to the developer (me)
* [] the second is an 'info' tab
* [] the third is a log out button
* [] the fourth icon/button adds a new reminder
* [] on the new reminder page, user can add a title, description, time and date

The folowing **extensions** are implemented:

* import android.app.AlertDialog
* import android.app.Dialog
* import android.content.Context
* import android.os.Bundle
* import android.util.Log
* import androidx.fragment.app.DialogFragment
* import android.os.Bundle
* import android.view.LayoutInflater
* import android.view.View
* import android.view.ViewGroup
* import androidx.fragment.app.Fragment
* import androidx.lifecycle.Observer
* import androidx.lifecycle.ViewModelProvider
* import androidx.navigation.findNavController
* import com.example.c323p6notes.databinding.FragmentEditNoteBinding
* import androidx.lifecycle.LiveData
* import androidx.lifecycle.MutableLiveData
* import androidx.lifecycle.ViewModel
* import androidx.lifecycle.viewModelScope
* import kotlinx.coroutines.launch
* import java.lang.IllegalArgumentException
* import androidx.appcompat.app.AppCompatActivity
* import androidx.room.ColumnInfo
* import androidx.room.Entity
* import androidx.room.PrimaryKey
* import androidx.room.Dao
* import androidx.room.Delete
* import androidx.room.Insert
* import androidx.room.Query
* import androidx.room.Update
* import androidx.room.Database
* import androidx.room.Room
* import androidx.room.RoomDatabase
* import androidx.recyclerview.widget.DiffUtil
* import androidx.recyclerview.widget.ListAdapter
* import androidx.recyclerview.widget.RecyclerView
* import com.example.c323p6notes.databinding.NoteItemBinding
  
## Video Walkthrough 






https://github.com/aublwill/c322.bonus.Reminders/assets/143005409/9617b42c-2aaa-43ba-952f-3147c53bf767








## Notes
* App is not fully implemented
*   Does not save when using the back button
*   About and info buttons don't do anything

## License
Copyright [2023] [Aubrey Williams]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
