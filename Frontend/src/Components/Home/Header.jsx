import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AuthModal from "../Modals/AuthModal";

const Header = () => {
  const navigate = useNavigate();
  const [isAuthModalOpened, setIsAuthModalOpened] = useState(false);

  return (
    <header className="header">
      <nav>
        <div className="nav__header">
          <div className="nav__logo">
            <Link to="#">
              <img src="assets/logo.png" alt="logo" />
              SkillShare
            </Link>
          </div>
          <div className="nav__menu__btn" id="menu-btn">
            <span>
              <i className="ri-menu-line"></i>
            </span>
          </div>
        </div>
        <ul className="nav__links" id="nav-links">
          <li className="link">
            <Link to="/">Contact Us</Link>
          </li>
          <li className="link">
            <Link to="#browse-skills">Browse Skills</Link>
          </li>
          <li className="link">
          <li className="link">
            <Link
              to="/community"
              onClick={() => {
                if (!localStorage.getItem("userId")) {
                  setIsAuthModalOpened(true); // Open the authentication modal if not logged in
                }
              }}
            >
              Share Skills
            </Link>
          </li>

          </li>
          <li className="link">
            <button
              onClick={() => {
                if (localStorage.getItem("userId")) {
                  navigate("/community"); // Navigate to the community page
                } else {
                  setIsAuthModalOpened(true); // Open the authentication modal
                }
              }}
              className="btn"
            >
              Join the Community
            </button>
          </li>
        </ul>
      </nav>
      <div className="section__container header__container" id="home">
        <div>
          <img src="assets/Layer2.png" alt="header" />
        </div>
        <div className="header__content">
          <h4>Learn New Skills &</h4>
          <h1 className="section__header">Empower Yourself!</h1>
          <p>
            Explore a wide range of skills, from coding to cooking, photography, DIY crafts, and more. Join today and start learning or sharing your expertise with others.
          </p>
          <div className="header__btn">
            <button
              onClick={() => {
                if (localStorage.getItem("userId")) {
                  navigate("/community"); // Navigate to the community page
                } else {
                  setIsAuthModalOpened(true); // Open the authentication modal
                }
              }}
              className="btn"
            >
              Join Today
            </button>
          </div>
        </div>
      </div>
      <AuthModal
        onClose={() => {
          setIsAuthModalOpened(false);
        }}
        isOpen={isAuthModalOpened}
      />
    </header>
  );
};

export default Header;
