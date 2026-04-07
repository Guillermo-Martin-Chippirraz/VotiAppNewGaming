import { useNavigate } from "react-router-dom"
import { StartButton } from "../components/StartButton";

export const HomePage = () => {
    const navigate = useNavigate();

    const handleStart = () => {
        navigate("/login");
    };

    return (
        <div
            style={{
                minHeight: "100vh",
                display: "flex",
                flexDirection: "column",
            }}
        >
            <header
                style={{
                    padding: "1rem",
                    textAlign: "center",
                }}
            >
                <h1>Imagotipo</h1>
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
                <h2>¡Toca para empezar!</h2>

                <div style={{ marginTop: "2rem" }}>
                    <StartButton onClick={handleStart} />
                </div>
            </main>
        </div>
    );
};