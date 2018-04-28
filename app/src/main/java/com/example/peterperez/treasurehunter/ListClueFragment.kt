package com.example.peterperez.treasurehunter

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list_clue.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListClueFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListClueFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListClueFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var chip_table_tv=chip_table_tv
        var chipList:List<ClueChip>
        chipList=MainActivity.cluechipdb?.clueChipDao()!!.getAllClueChips()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_clue, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
                ListClueFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
