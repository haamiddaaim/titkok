package com.example.tiktok

import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment(R.layout.fragment_third) {
    // short way to Inflate the layout for this fragment




   /* lateinit var binding: ActivityFragmentsBinding
    override fun onCreateView(
        //onCreateView purpose is to inflate our view of the fragment so it will take this layout file and return it as a view so that this fragment knows which view to show
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    } */

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//      binding.btnFrag1
//        //if you want to access the fragment views...you need to override onViewCreated which is called when all the views are created
//    }
   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)*/
        //can't treat onCreate fun in fragment like in an activity
        //in activities we set the content view in oncreate but fragments don't since they have the onCreateView fun which creates that view
        //oncreateView is called after oncreate so we can't access the views of fragments inside of the oncreate fun
        //for example if we have an id of a fragment in oncreate it will crash since this is view is not created yet


    }
