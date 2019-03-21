package com.task2trip.android.UI.Fragment.Profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.BuildConfig
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.ImageLoader.ImageCropType
import com.task2trip.android.Model.ImageLoader.ImageLoader
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
import com.task2trip.widgetlibrary.LoadingAndMessage
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.File

class ProfileFragment : BaseFragment(), UserView, ItemClickListener<UserCategoryForUsed> {
    private lateinit var presenter: UserPresenter
    private var user: UserImpl = MockData.getEmptyUser()
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

    private fun showPhotoCamera() {
        if (Build.VERSION.SDK_INT < 23) {
            startCamera()
        } else {
            activity?.let {
                if (ActivityCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(it, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissionPhoto()
                } else {
                    startCamera()
                }
            }
        }
    }

    private fun startCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        context?.let {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(it, BuildConfig.APPLICATION_ID + ".provider", file))
        }
        startActivityForResult(intent, Constants.REQUEST_CAMERA_PHOTO)
    }

    private fun getStoragePhoto() {
        if (Build.VERSION.SDK_INT < 23) {
            openSystemStorage()
        } else {
            context?.let {
                if (ActivityCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(it, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissionStorage()
                } else {
                    openSystemStorage()
                }
            }
        }
    }

    private fun openSystemStorage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        startActivityForResult(chooser, Constants.REQUEST_STORAGE_BROWSER)
    }

    private fun requestPermissionStorage() {
        requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
            Constants.REQUEST_PERMISSION_STORAGE)
    }

    private fun requestPermissionPhoto() {
        requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
            Constants.REQUEST_PERMISSION_PHOTO)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.REQUEST_PERMISSION_PHOTO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            }
        } else if (requestCode == Constants.REQUEST_PERMISSION_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                openSystemStorage()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            Constants.REQUEST_DIALOG_CAMERA_SOURCE -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.let {
                        val source = it.getStringExtra(Constants.EXTRA_DIALOG_PHOTO_SOURCE)
                        if (source == Constants.EXTRA_PHOTO_SOURCE_CAMERA) {
                            showPhotoCamera()
                        } else if (source == Constants.EXTRA_PHOTO_SOURCE_STORAGE) {
                            getStoragePhoto()
                        }
                    }
                }
            }
            Constants.REQUEST_CAMERA_PHOTO -> {
                if (resultCode == Activity.RESULT_OK) {
                    ImageLoader(file, ivProfilePhoto)
                    presenter.saveImageAvatar(file)
                }
            }
            Constants.REQUEST_STORAGE_BROWSER -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.let {
                        val path = getPath(it.data)
                        //val img = File(it.data.path)
                    }
                    //presenter.saveImageAvatar(file)
                }
            }
        }
    }

    private fun getPath(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context?.contentResolver?.query(uri, projection, null, null, null) ?: return null
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val s = cursor.getString(column_index)
        cursor.close()
        return s
    }

    override fun onMySelfInfoResult(user: UserImpl) {
        this.user = user
        ImageLoader(user.getProfile().getImageAvatarUrl(), ivProfilePhoto, ImageCropType.CROP_CIRCLE)
    }

    override fun onProgress(isProgress: Boolean) {
        if (isProgress) {
            viewLoadAndMessage.show()
        } else {
            viewLoadAndMessage.hide()
        }
        viewLoadAndMessage.setProgress(isProgress)
    }

    override fun onUploadImageAvatarResult(isSuccess: Boolean) {
        if (isSuccess) {
            viewLoadAndMessage.setMessage("Фотография успешно загружена!", LoadingAndMessage.SHOW_MIDDLE)
        } else {
            onMessage("При загрузке возникли ошибки. Попробуйте позже!")
        }
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
