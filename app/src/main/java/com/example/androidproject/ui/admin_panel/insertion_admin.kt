package com.example.androidproject.ui.admin_panel





import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidproject.R
import com.example.androidproject.ui.services.ItemsViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class insertion_admin : AppCompatActivity() {

    private lateinit var etgoodsname: EditText
    private lateinit var etgdscharge: EditText
    private lateinit var etpickImageButton: Button
    private lateinit var etgoodimage: ImageView
    private lateinit var etenertbtn: Button

    private lateinit var dbRef: DatabaseReference

    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion_admin)

        etgoodsname = findViewById(R.id.goodsName)
        etgdscharge = findViewById(R.id.gdscharge)
        etpickImageButton = findViewById(R.id.pickImageButton)
        etgoodimage = findViewById(R.id.goodimage)
        etenertbtn = findViewById(R.id.enterbtn)

        dbRef = FirebaseDatabase.getInstance().getReference("Goods Shipped")

        etpickImageButton.setOnClickListener {
            // Create an intent to pick an image from the gallery
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        etenertbtn.setOnClickListener {
            saveEmployeeData()
        }
    }

    private fun saveEmployeeData() {
        // getting values
        val goodsname = etgoodsname.text.toString()
        val gdscharge = etgdscharge.text.toString()
        val goodimage = etgoodimage.tag?.toString() ?: "" // Using the tag to store the image URI

        if (goodsname.isEmpty()) {
            etgoodsname.error = "Please enter name"
            return
        }
        if (gdscharge.isEmpty()) {
            etgdscharge.error = "Please enter age"
            return
        }
        if (goodimage.isEmpty()) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
            return
        }

        val goodId = dbRef.push().key!!

        val goods = ItemsViewModel(goodId, goodsname, gdscharge, goodimage)

        dbRef.child(goodId).setValue(goods)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etgoodsname.text.clear()
                etgdscharge.text.clear()
                etgoodimage.setImageURI(null) // Clear the selected image

            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

    // This method will be called when the image selection is done
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            // Set the selected image URI to the ImageView and store it in the tag
            etgoodimage.setImageURI(selectedImageUri)
            etgoodimage.tag = selectedImageUri.toString()
        }
    }
}
