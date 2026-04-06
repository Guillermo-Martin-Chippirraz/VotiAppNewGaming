import { LoginForm } from "../components/LoginForm"

export const LoginPage = () => {
    return (
        <div style={{ minHeight: "100vh", display: "flex", flexDirection: "column" }}>
            {/* Header */}
            <header style={{ padding: "1rem" }}>
                <h2>Bienvenido/a de nuevo</h2>
            </header>

            {/* Contenido */}
            <main
                style={{
                    flex: 1,
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center",
                    alignItems: "center",
                    padding: "1rem",
                }}
            >
                <h1>Iniciar Sesión</h1>

                <div
                    style={{
                        width: "100%",
                        maxWidth: "400px",
                        marginTop: "1.5rem",
                    }}
                >
                    <LoginForm />
                </div>
            </main>
        </div>
    );
};