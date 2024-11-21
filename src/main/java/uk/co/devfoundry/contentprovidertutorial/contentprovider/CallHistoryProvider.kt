package uk.co.devfoundry.contentprovidertutorial.contentprovider

import android.content.ContentResolver
import android.database.Cursor
import android.provider.CallLog
import android.util.Log
import java.util.Date

class CallHistoryProvider {
        fun fetchCallHistory(contentResolver: ContentResolver): List<CallLogEntry> {
            // Define the columns to retrieve
            val projection = arrayOf(
                CallLog.Calls._ID,
                CallLog.Calls.NUMBER,
                CallLog.Calls.TYPE,
                CallLog.Calls.DATE,
                CallLog.Calls.DURATION
            )

            // Query the CallLog Content Provider
            val cursor: Cursor? = contentResolver.query(
                CallLog.Calls.CONTENT_URI,
                projection,
                null,  // No selection
                null,  // No selection arguments
                "${CallLog.Calls.DATE} DESC" // Order by most recent call first
            )

            val callLogEntries = mutableListOf<CallLogEntry>()

            // Check if cursor is not null and process the results
            cursor?.use {
                while (it.moveToNext()) {
                    val id = it.getLong(it.getColumnIndexOrThrow(CallLog.Calls._ID))
                    val number = it.getString(it.getColumnIndexOrThrow(CallLog.Calls.NUMBER))
                    val type = it.getInt(it.getColumnIndexOrThrow(CallLog.Calls.TYPE))
                    val dateMillis = it.getLong(it.getColumnIndexOrThrow(CallLog.Calls.DATE))
                    val duration = it.getString(it.getColumnIndexOrThrow(CallLog.Calls.DURATION))

                    // Convert date from milliseconds
                    val callDate = Date(dateMillis)
                    val callType = when (type) {
                        CallLog.Calls.INCOMING_TYPE -> "Incoming"
                        CallLog.Calls.OUTGOING_TYPE -> "Outgoing"
                        CallLog.Calls.MISSED_TYPE -> "Missed"
                        else -> "Other"
                    }

                    // Log the call
                    Log.d(
                        "CallLog",
                        "Number: $number, Type: $callType, Date: $callDate, Duration: $duration seconds"
                    )

                    // Add to list of CallLogEntry
                    callLogEntries.add(
                        CallLogEntry(
                            id,
                            number,
                            callType,
                            callDate.toString(),
                            duration
                        )
                    )
                }
            }

            // Return the list of call log entries
            return callLogEntries
        }
    }


data class CallLogEntry(
    val id: Long,
    val number: String,
    val type: String,
    val date: String,
    val duration: String
)

