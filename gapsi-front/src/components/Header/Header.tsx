import React from "react";
import { AppBar, Toolbar, Typography, IconButton } from "@mui/material";
import { MoreVert as MoreVertIcon } from "@mui/icons-material";
import logoImg from "../../assets/images/logo-gapsi.png";
import "./Header.css";

const Header: React.FC = () => {
  return (
    <AppBar position="static" className="app-header">
      <Toolbar>
        <img src={logoImg} alt="Gapsi Logo" className="header-logo" />
        <Typography variant="h6" component="h1">
          e-Commerce Gapsi
        </Typography>
        <div style={{ flexGrow: 1 }} />
        <IconButton color="inherit">
          <MoreVertIcon />
        </IconButton>
      </Toolbar>
    </AppBar>
  );
};

export default Header;
