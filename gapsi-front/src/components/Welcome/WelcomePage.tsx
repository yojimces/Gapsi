import React, { useEffect, useState } from "react";
import {
  Container,
  Paper,
  Typography,
  CircularProgress,
  Button,
} from "@mui/material";
import { getWelcomeData } from "../../services/appService";
import type { WelcomeData } from "../../types/api";
import candidatoImg from "../../assets/images/candidato.jpg";
import "./WelcomePage.css";

interface WelcomePageProps {
  onNavigateToProviders?: () => void;
}

const WelcomePage: React.FC<WelcomePageProps> = ({ onNavigateToProviders }) => {
  const [loading, setLoading] = useState<boolean>(true);
  const [data, setData] = useState<WelcomeData | null>(null);

  useEffect(() => {
    const fetchWelcomeData = async () => {
      try {
        const response = await getWelcomeData();
        setData(response);
      } catch (error) {
        console.error("Error fetching welcome data:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchWelcomeData();
  }, []);

  if (loading) {
    return (
      <Container className="welcome-container">
        <CircularProgress />
      </Container>
    );
  }

  return (
    <Container className="welcome-container">
      <Paper elevation={3} className="welcome-card">
        <img
          src={candidatoImg}
          alt="Candidato"
          className="candidato-image fade-in"
        />
        <Typography variant="h4" className="welcome-text slide-in">
          Bienvenido Yoel Jiménez
        </Typography>
        {onNavigateToProviders && (
          <Button
            variant="contained"
            color="primary"
            size="large"
            onClick={onNavigateToProviders}
            sx={{ mt: 4 }}
          >
            Continuar
          </Button>
        )}
      </Paper>
      <Typography variant="body2" className="version-text">
        versión {data?.version || "1.0.0"}
      </Typography>
    </Container>
  );
};

export default WelcomePage;
