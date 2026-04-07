interface StartButtonProps {
    onClick: () => void;
}

export const StartButton = ({ onClick }: StartButtonProps) => {
    return (
        <button
            onClick={onClick}
            style={{
                width: "150px",
                height: "150px",
                borderRadius: "50%",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
            }}
        >
            <span>Isotipo</span>
        </button>
    )
}