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
        <div className="home-container">
            <header
                style={{
                    padding: "1rem",
                    textAlign: "center",
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "center",
                    alignItems: "center",
                    gap: "1rem",
                }}
            >
                <img src={ISOTIPO} alt="Isotipo NewGaming" className="home-iso"/>
                <img src={LOGOTIPO} alt="Logotipo NewGaming" className="home-logo" />
            </header>

            <main
                style={{
                    flex: 1,
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center",
                    alignItems: "center",
                    padding: "1rem",
                    textAlign: "center",
                }}
            >
                <h2 className="home-title">¡Toca para empezar!</h2>

                <div style={{ marginTop: "2rem" }}>
                    <StartButton onClick={handleStart} />
                </div>
            </main>
        </div>
    );
};