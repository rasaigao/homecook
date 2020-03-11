const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();


// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
 exports.postFeedItem = functions.https.onCall((data, context) => {
    const snapshot = await admin.database().ref('/feedItems').push({data});
    response.redirect(303, snapshot.ref.toString());
 });
