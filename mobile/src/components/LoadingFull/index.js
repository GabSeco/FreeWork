import * as React from 'react';
import { ActivityIndicator } from 'react-native';
import { AreaTotal, TextLoad } from './styled';


export default function LoadingFull({open}) {

  return (
        <>
            {open && 
                <AreaTotal>
                    <ActivityIndicator size="large" color="#000fff" />
                    <TextLoad>Carregando</TextLoad>
                </AreaTotal>
            }
        </>
  );
}