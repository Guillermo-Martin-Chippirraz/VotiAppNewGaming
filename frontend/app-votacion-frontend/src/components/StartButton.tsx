import "./StartButton.css";
import BOTON from "../assets/images/BOTON.webp";

interface StartButtonProps {
    onClick: () => void;
}

export const StartButton = ({ onClick }: StartButtonProps) => {
    return (
        <button
            onClick={onClick}
            className="start-button"
        >
            <img src={BOTON} alt="Comenzar" className="start-button-icon" />
        </button>
    )
}