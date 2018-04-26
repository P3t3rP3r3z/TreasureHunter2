package com.example.peterperez.treasurehunter

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

/* portions of this code were inspired by or use code from the following guides and projects:

-https://www.andreasjakl.com/nfc-tags-ndef-and-android-with-kotlin/
-https://www.learn2crack.com/2016/10/android-reading-and-writing-nfc-tags.html
-https://android.jlelse.eu/writing-to-a-nfc-tag-on-android-8d58f5e3c1fc
-https://medium.com/@ajaysaini.official/building-database-with-room-persistence-library-ecf7d0b8f3e9
-https://expertise.jetruby.com/a-complete-guide-to-implementing-nfc-in-a-kotlin-application-5a94c5baf4dd


 */

class MainActivity : AppCompatActivity(), Listener {
    private var chipNumber : Int =0
    private var mEtMessage: EditText? = null
    private var mBtWrite: Button? = null
    private var mBtRead: Button? = null
    private var clue:EditText? = null

    private var mNfcWriteFragment: NFCWriteFragment? = null
    private var mNfcReadFragment: NFCReadFragment? = null

    private var isDialogDisplayed = false
    private var isWrite = false

    private var mNfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initNFC()
    }

    private fun initViews() {

        mEtMessage = edit_Text as EditText
        mBtWrite = write_button as Button
        mBtRead = read_button as Button

        mBtWrite!!.setOnClickListener { view -> showWriteFragment() }
        mBtRead!!.setOnClickListener { view -> showReadFragment() }
    }

    private fun initNFC() {

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
    }


    private fun showWriteFragment() {

        isWrite = true

        mNfcWriteFragment = fragmentManager.findFragmentByTag(NFCWriteFragment.TAG) as? NFCWriteFragment

        if (mNfcWriteFragment == null) {

            mNfcWriteFragment = NFCWriteFragment.newInstance()
        }
        mNfcWriteFragment!!.show(fragmentManager, NFCWriteFragment.TAG)

    }

    private fun showReadFragment() {

        mNfcReadFragment = fragmentManager.findFragmentByTag(NFCReadFragment.TAG) as? NFCReadFragment

        if (mNfcReadFragment == null) {

            mNfcReadFragment = NFCReadFragment.newInstance()
        }
        mNfcReadFragment!!.show(fragmentManager, NFCReadFragment.TAG)

    }

    override fun onDialogDisplayed() {

        isDialogDisplayed = true
    }

    override fun onDialogDismissed() {

        isDialogDisplayed = false
        isWrite = false
    }

    override fun onResume() {
        super.onResume()
        val tagDetected = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        val ndefDetected = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
        val techDetected = IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED)
        val nfcIntentFilter = arrayOf(techDetected, tagDetected, ndefDetected)

        val pendingIntent = PendingIntent.getActivity(
                this, 0, Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0)
        if (mNfcAdapter != null)
            mNfcAdapter!!.enableForegroundDispatch(this, pendingIntent, nfcIntentFilter, null)

    }

    override fun onPause() {
        super.onPause()
        if (mNfcAdapter != null)
            mNfcAdapter!!.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent) {
        val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)

        Log.d(TAG, "onNewIntent: " + intent.action!!)

        if (tag != null) {
            Toast.makeText(this, getString(R.string.message_tag_detected), Toast.LENGTH_SHORT).show()
            val ndef = Ndef.get(tag)

            if (isDialogDisplayed) {

                if (isWrite) {
                    if(chipNumber==100){
                        chipNumber=0
                    }
                    val messageToWrite = ":"+chipNumber+":"+mEtMessage!!.text.toString()+":"
                    mNfcWriteFragment = fragmentManager.findFragmentByTag(NFCWriteFragment.TAG) as NFCWriteFragment
                    mNfcWriteFragment!!.onNfcDetected(ndef, messageToWrite)

                } else {

                    mNfcReadFragment = fragmentManager.findFragmentByTag(NFCReadFragment.TAG) as NFCReadFragment
                    mNfcReadFragment!!.onNfcDetected(ndef)
                }
            }
        }
    }

    companion object {

        val TAG = MainActivity::class.java!!.getSimpleName()
    }
}
