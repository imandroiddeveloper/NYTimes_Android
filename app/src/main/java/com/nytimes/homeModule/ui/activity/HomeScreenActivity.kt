package com.nytimes.homeModule.ui.activity

import android.os.Bundle
import android.telephony.mbms.MbmsErrors
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.nytimes.R
import com.nytimes.homeModule.interfaces.IMostPopularClickEvent
import com.nytimes.homeModule.model.MostPopular
import com.nytimes.homeModule.model.MostPopularResponse
import com.nytimes.homeModule.ui.adapter.MostPopularAdapter
import com.nytimes.homeModule.viewModel.NYTimesViewModel
import java.lang.Exception

class HomeScreenActivity : AppCompatActivity() , IMostPopularClickEvent {
    var bottomSheet: LinearLayoutCompat? = null
    var behavior: BottomSheetBehavior<*>? = null

    lateinit var mostPopularRecyclerView: RecyclerView
    lateinit var toolBar: Toolbar
    lateinit var llProgressView : LinearLayout
    private val mostPopularArrayList = ArrayList<MostPopular>();
    var mostPopularAdapter: MostPopularAdapter? = null
    var nyTimesViewModel: NYTimesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)

        initializationView();
        initializationViewModel();
        initializationAdapter();
        initializationObserver();

    }


    /*
  * This method use for binding View
  * */
    private fun initializationView() {
        mostPopularRecyclerView = findViewById(R.id.mostPopularRecyclerView);
        toolBar = findViewById(R.id.toolBar)
        llProgressView = findViewById(R.id.llProgressView)

        setSupportActionBar(toolBar)
        title = getString(R.string.LBL_NY_TIMES_MOST_POPULAR)
        toolBar.setTitleTextColor(resources.getColor(R.color.colorWhite))

        mostPopularRecyclerView.layoutManager = LinearLayoutManager(this)

        reviewItemDetailSheetInitialization()
    }

    /*
   * This method use for initialize ViewModel
   * */
    private fun initializationViewModel() {
        nyTimesViewModel = ViewModelProvider(this).get(NYTimesViewModel::class.java)
        llProgressView.visibility = View.VISIBLE
        nyTimesViewModel?.getMostPopular("7")
    }

    /*
    * This method use for declare adapter
    * */
    private fun initializationAdapter() {
        mostPopularAdapter = MostPopularAdapter(mostPopularArrayList,this);
        mostPopularRecyclerView.adapter = mostPopularAdapter
    }

    /*
    * This method use to display item detail on recylerview click event
    * */
    private fun reviewItemDetailSheetInitialization() {
        bottomSheet = findViewById(R.id.design_bottom_sheet)
        behavior = BottomSheetBehavior.from(bottomSheet!!)
        bottomSheet?.findViewById<View>(R.id.ReviewBackArrow)?.setOnClickListener { v: View? ->
            behaviorVisibility(false)
        }

        behavior!!.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }

    /*
     * This method use for manage observer action
     * */
    private fun initializationObserver() {
        nyTimesViewModel?.mutableLiveData?.observe(this, { res ->
            llProgressView.visibility = View.GONE
            if(res is String){
                Toast.makeText(this,res, Toast.LENGTH_SHORT).show()
            }
            else {
                try {
                    val response = res as MostPopularResponse
                    if(response.status == "OK"){
                        Toast.makeText(this, getString(R.string.LBL_TOTAL_RESULT_DISPLAY)+response.num_results,Toast.LENGTH_LONG).show()
                        mostPopularArrayList?.addAll(response.mostPopulars!!)
                        mostPopularAdapter?.notifyDataSetChanged()
                    }else{
                        Toast.makeText(this,getString(R.string.LBL_NO_RECORD_FOUND),Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Log.e("exc", e.printStackTrace().toString())
                }
            }
        }
        )
    }

    private fun behaviorVisibility(b: Boolean) {
        if (b) behavior!!.setState(BottomSheetBehavior.STATE_EXPANDED) else {
            behavior!!.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }
    }

    override fun onClickEvent(mostPopular: MostPopular) {
        behaviorVisibility(true)
    }

}