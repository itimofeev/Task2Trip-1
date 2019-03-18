package com.task2trip.android.UI.Fragment.Profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.User.ProfileImpl
import com.task2trip.android.Model.User.UserCategoryForUsed
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.Model.User.UserRole
import com.task2trip.android.Presenter.UserPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.ProfileMainCategoryAdapter
import com.task2trip.android.UI.Dialog.SelectPhotoSourceDialog
import com.task2trip.android.UI.Dialog.show
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.UserView
import kotlinx.android.synthetic.main.fragment_profile.*
import android.net.Uri
import android.os.Environment
import java.io.File
import android.graphics.Bitmap
import androidx.core.content.FileProvider
import com.task2trip.android.BuildConfig
import com.task2trip.widgetlibrary.LoadingAndMessage

class ProfileFragment : BaseFragment(), UserView, ItemClickListener<UserCategoryForUsed> {
    private lateinit var presenter: UserPresenter
    private var user: UserImpl = MockData.getEmptyUser()
    private var outputFileUri: Uri? = null
    private val file = File(Environment.getExternalStorageDirectory(), Constants.FILE_NAME_AVATAR)

    override fun getArgs(args: Bundle?) {
        args?.let {
            user = it.getParcelable(Constants.EXTRA_USER) ?: MockData.getEmptyUser()
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_profile
    }

    override fun initComponents(view: View) {
        initPresenter(view)
        tvProfilePhoto.setOnClickListener {
            onChangePhotoClick()
        }
        ivProfilePhoto.setOnClickListener {
            onChangePhotoClick()
        }
        btShowMyTasks.setOnClickListener {
            onShowMyTasksClick()
        }
        if (user.getRole() == UserRole.TRAVELER) {
            groupLevelUp.visibility = View.VISIBLE
            groupLevelUp.setOnClickListener {
                onUserLevelUp()
            }
        } else {
            groupLevelUp.visibility = View.GONE
        }
        tvExecuteTasks.setOnClickListener {
            onUserLevelUp()
        }
        tvTaskExecuteDescription.setOnClickListener {
            onUserLevelUp()
        }
        initAboutUserCategoryList(view)
    }

    private fun initPresenter(view: View) {
        presenter = UserPresenter(this, view.context)
        presenter.getUserInfo()
    }

    private fun initAboutUserCategoryList(view: View) {
        val items = ArrayList<UserCategoryForUsed>()
        if (user.getRole() == UserRole.LOCAL) {
            items.add(UserCategoryForUsed(R.id.profileCategoryFragment, getString(R.string.profile_category), ""))
        }
        items.add(UserCategoryForUsed(R.id.profileMainInfoFragment, getString(R.string.profile_main), ""))
        items.add(UserCategoryForUsed(R.id.profileContactsFragment, getString(R.string.profile_contacts), ""))
        items.add(UserCategoryForUsed(R.id.profileAboutFragment, getString(R.string.profile_about), ""))

        val adapter = ProfileMainCategoryAdapter(items)
        adapter.setClickListener(this)
        rvUserCategoryList.setHasFixedSize(true)
        rvUserCategoryList.layoutManager = LinearLayoutManager(view.context)
        rvUserCategoryList.adapter = adapter
    }

    override fun onItemClick(item: UserCategoryForUsed, position: Int) {
        val args = Bundle()
        val profile: ProfileImpl = user.getProfile() as ProfileImpl
        args.putParcelable(Constants.EXTRA_PROFILE, profile)
        args.putBoolean(Constants.EXTRA_USER_LEVEL_UP, false)
        navigateTo(item.id, args)
    }

    private fun onChangePhotoClick() {
        val dialog = SelectPhotoSourceDialog()
        dialog.show(this)
    }

    private fun getPhotoCamera() {
        //startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), Constants.REQUEST_CAMERA_PHOTO)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        outputFileUri = FileProvider.getUriForFile(context!!, BuildConfig.APPLICATION_ID + ".provider", file)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
        startActivityForResult(intent, Constants.REQUEST_CAMERA_PHOTO)
    }

    private fun getStoragePhoto() {
        if (Build.VERSION.SDK_INT < 23) {
            openSystemStorage()
        } else {
            activity?.let {
                if (ActivityCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //requestContactsPermissions()
                } else {
                    openSystemStorage()
                }
            }
        }
    }

    private fun openSystemStorage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent, Constants.REQUEST_STORAGE_BROWSER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            Constants.REQUEST_CAMERA_PHOTO -> {
                if (resultCode == Activity.RESULT_OK) {
                    if (outputFileUri != null) {
                        context?.contentResolver?.notifyChange(outputFileUri, null)
                    }
                    val cr = context?.contentResolver
                    try {
                        val bitmap: Bitmap? = android.provider.MediaStore.Images.Media.getBitmap(cr, outputFileUri)
                        bitmap?.let {
                            ivProfilePhoto.setImageBitmap(it)
                        }
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                    presenter.saveImageAvatar(file)
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    //
                }
            }
            Constants.REQUEST_DIALOG_CAMERA_SOURCE -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.let {
                        val source = it.getStringExtra(Constants.EXTRA_DIALOG_PHOTO_SOURCE)
                        if (source == Constants.EXTRA_PHOTO_SOURCE_CAMERA) {
                            getPhotoCamera()
                        } else if (source == Constants.EXTRA_PHOTO_SOURCE_STORAGE) {
                            getStoragePhoto()
                        }
                    }
                }
            }
            Constants.REQUEST_STORAGE_BROWSER -> {
                if (resultCode == Activity.RESULT_OK) {
                    if (outputFileUri != null) {
                        context?.contentResolver?.notifyChange(outputFileUri, null)
                    }
                    val cr = context?.contentResolver
                    try {
                        val bitmap: Bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, outputFileUri)
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                    presenter.saveImageAvatar(File(""))
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    //
                }
            }
        }
    }

    override fun onMySelfInfoResult(user: UserImpl) {
        this.user = user
    }

    override fun onProgress(isProgress: Boolean) {
        if (isProgress) {
            viewLoadAndMessage.show()
        } else {
            viewLoadAndMessage.hide()
        }
        viewLoadAndMessage.setProgress(isProgress)
    }

    override fun onUploadImageAvatarResult() {
        viewLoadAndMessage.setMessage("Фотография успешно загружена!", LoadingAndMessage.SHOW_MIDDLE)
    }

    private fun onUserLevelUp() {
        val args = Bundle()
        val profile: ProfileImpl = user.getProfile() as ProfileImpl
        args.putParcelable(Constants.EXTRA_PROFILE, profile)
        args.putBoolean(Constants.EXTRA_USER_LEVEL_UP, true)
        navigateTo(R.id.profileCategoryFragment, args)
    }

    private fun onShowMyTasksClick() {
        navigateTo(R.id.taskListPerformerFragment, Bundle())
    }

    override fun onUserResult(user: UserImpl) {
        //
    }
}
