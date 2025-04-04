// Import necessary functions from Firebase SDK
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
import { getStorage } from "firebase/storage";

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyCjuWxp-qbmDPJj0JePxhzB66gzJAPmcLI",
  authDomain: "skill-sharing-platform-df39d.firebaseapp.com",
  projectId: "skill-sharing-platform-df39d",
  storageBucket: "skill-sharing-platform-df39d.appspot.com",
  messagingSenderId: "6810545495",
  appId: "1:6810545495:web:7ab740cecf22bc62dd6212",
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

// Initialize Firestore, Auth, and Storage services
export const db = getFirestore(app);
export const auth = getAuth(app);
export const storage = getStorage(app);
