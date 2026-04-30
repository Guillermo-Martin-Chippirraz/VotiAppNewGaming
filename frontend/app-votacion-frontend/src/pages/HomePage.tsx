import { useNavigate } from "react-router-dom"
import { StartButton } from "../components/StartButton";
import ISOTIPO from "../assets/images/LOGO.webp";
import LOGOTIPO from "../assets/images/NEWGAMING.webp";
import "./HomePage.css";

export const HomePage = () => {
    const navigate = useNavigate();

    const handleStart = () => {
        navigate("/login");
    };

    return (
        <div className="pantalla-movil-base home-container">
            <header className="home-header">
                <img src={ISOTIPO} alt="Isotipo NewGaming" className="home-iso"/>
                <img src={LOGOTIPO} alt="Logotipo NewGaming" className="home-logo" />
            </header>

            <main className="home-main">
                <h2 className="home-title">¡Toca para empezar!</h2>
                <div className="home-button-wrapper">
                    <StartButton onClick={handleStart} />
                </div>
            </main>
        </div>
    );
};