// src/firebaseConfig.js

import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
import { getStorage } from "firebase/storage";

// Your Firebase config details (project specific details)
const firebaseConfig = {
  apiKey: "AIzaSyCjuWxp-qbmDPJj0JePxhzB66gzJAPmcLI",  // apiKey
  authDomain: "skill-sharing-platform-df39d.firebaseapp.com", // authDomain
  projectId: "skill-sharing-platform-df39d",  // projectId
  storageBucket: "skill-sharing-platform-df39d.appspot.com", // storageBucket
  messagingSenderId: "6810545495",  // messagingSenderId
  appId: "1:6810545495:web:7ab740cecf22bc62dd6212",  // appId
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

// Firebase services
export const db = getFirestore(app);    // Firestore
export const auth = getAuth(app);        // Auth
export const storage = getStorage(app);  // Storage
