<?php

namespace VetsBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use VetsBundle\Entity\Tarif;
use VetsBundle\Entity\TestVet;

class TarifController extends Controller
{
    public function BudgetAction(Request $request) //voilà, dima 5alta 3liya hal request, hiya mbalbzettha, 5ater normalement t3addi el id en pareametre fel route, kima warritkom
    {
        $em=$this->getDoctrine()->getManager();
        $idveto=$request->get('idvet');
        $budget=$em->getRepository(TestVet::class)->findBudget($idveto);
        return $this->render('VetsBundle:Tarif:list_tarif.html.twig', array('budget'=>$budget));
    }

    public function CalculerBudgetAction($consultation, $chat, $chien,$ster,$analyse){ //bon hiya tnajjem matest7a99hech hedhi jimla, kima t7eb, t7eb ne5dmou el funcition linna wela bel js? enehou khir? kifkif, 5alli linna mela bech tsammiha ajax, lo5ra le 5ater

             $total=$consultation+$chat+$chien+$ster+$analyse; //id athika just bech nib biha tarif taa kol veto// ey win sta3malttha linna fel function
           return new JsonResponse(['total' => $total]);

    }

    public function NewTarifAction(Request $request)
    {
        $Tarif=new Tarif();
        if($request->isMethod('POST'))
        {
            $em=$this->getDoctrine()->getManager();
            $Tarif->setIdVeto($request->get('id_veto'));
            $Tarif->setAnalyses($request->get('analyses'));
            $Tarif->setConsultation($request->get('consultation'));
            $Tarif->setVaccinationchat($request->get('vaccinationChat'));
            $Tarif->setVaccinationchien($request->get('vaccinationChien'));
            $Tarif->setSterilisation($request->get('sterilisation'));
            $em->persist($Tarif);
            $em->flush();

            return $this->redirectToRoute('list');



        }
        return $this->render('VetsBundle:Tarif:newTarif.html.twig');
    }


}
