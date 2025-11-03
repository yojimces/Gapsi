import React, { useEffect, useRef, useCallback } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchProviders } from "../../redux/slices/providerSlice";
import type { RootState, AppDispatch } from "../../redux/store";
import ProviderItem from "./ProviderItem";
import {
  Container,
  Paper,
  Typography,
  CircularProgress,
  Box,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";

const ProviderList: React.FC = () => {
  const dispatch = useDispatch<AppDispatch>();
  const { items, loading, currentPage, totalPages, totalElements } =
    useSelector((state: RootState) => state.providers);

  // Ref para el elemento observado (trigger de carga)
  const loadMoreRef = useRef<HTMLTableRowElement>(null);

  useEffect(() => {
    dispatch(fetchProviders({ page: 0 }));
  }, [dispatch]);

  // Callback para Intersection Observer
  const handleObserver = useCallback(
    (entries: IntersectionObserverEntry[]) => {
      const target = entries[0];
      if (target.isIntersecting && !loading && currentPage < totalPages - 1) {
        dispatch(fetchProviders({ page: currentPage + 1 }));
      }
    },
    [dispatch, loading, currentPage, totalPages]
  );

  useEffect(() => {
    // Configurar Intersection Observer
    const observer = new IntersectionObserver(handleObserver, {
      root: null,
      rootMargin: "100px",
      threshold: 0.1,
    });

    const currentRef = loadMoreRef.current;
    if (currentRef) {
      observer.observe(currentRef);
    }

    return () => {
      if (currentRef) {
        observer.unobserve(currentRef);
      }
    };
  }, [handleObserver]);

  return (
    <Container className="provider-list-container" maxWidth="lg">
      <Paper elevation={2} className="provider-list-paper">
        {/* Tabla de proveedores */}
        <TableContainer>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Estado</TableCell>
                <TableCell>Nombre</TableCell>
                <TableCell>Razón Social</TableCell>
                <TableCell>Activo</TableCell>
                <TableCell>Descripción</TableCell>
                <TableCell align="right">Valor</TableCell>
                <TableCell align="center">Acciones</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {items.length === 0 && !loading ? (
                <TableRow>
                  <TableCell colSpan={7} align="center">
                    <Typography
                      variant="body2"
                      color="text.secondary"
                      sx={{ py: 4 }}
                    >
                      No hay proveedores registrados
                    </Typography>
                  </TableCell>
                </TableRow>
              ) : (
                <>
                  {items.map((provider) => (
                    <ProviderItem key={provider.id} provider={provider} />
                  ))}
                  <TableRow ref={loadMoreRef}>
                    <TableCell colSpan={7} align="center">
                      {loading && (
                        <Box
                          sx={{
                            display: "flex",
                            justifyContent: "center",
                            py: 2,
                          }}
                        >
                          <CircularProgress size={30} />
                        </Box>
                      )}
                    </TableCell>
                  </TableRow>
                </>
              )}
            </TableBody>
          </Table>
        </TableContainer>

        {/* Loading inicial */}
        {loading && items.length === 0 && (
          <Box sx={{ display: "flex", justifyContent: "center", py: 4 }}>
            <CircularProgress />
          </Box>
        )}

        {/* Indicador de fin de lista */}
        {currentPage >= totalPages - 1 && items.length > 0 && !loading && (
          <Box sx={{ py: 2, textAlign: "center" }}>
            <Typography variant="body2" color="text.secondary">
              Mostrando {totalElements} proveedores
            </Typography>
          </Box>
        )}
      </Paper>
    </Container>
  );
};

export default ProviderList;
