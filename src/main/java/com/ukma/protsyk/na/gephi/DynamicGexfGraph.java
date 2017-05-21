package com.ukma.protsyk.na.gephi;

/**
 * Created by okpr0814 on 5/6/2017.
 */

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.friends.FriendStatus;
import com.vk.api.sdk.objects.friends.responses.GetResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.users.UserField;
import it.uniroma1.dis.wsngroup.gexf4j.core.Edge;
import it.uniroma1.dis.wsngroup.gexf4j.core.EdgeType;
import it.uniroma1.dis.wsngroup.gexf4j.core.Gexf;
import it.uniroma1.dis.wsngroup.gexf4j.core.Graph;
import it.uniroma1.dis.wsngroup.gexf4j.core.IntervalType;
import it.uniroma1.dis.wsngroup.gexf4j.core.Mode;
import it.uniroma1.dis.wsngroup.gexf4j.core.Node;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.Attribute;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeClass;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeList;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeType;
import it.uniroma1.dis.wsngroup.gexf4j.core.dynamic.Spell;
import it.uniroma1.dis.wsngroup.gexf4j.core.dynamic.TimeFormat;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.GexfImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.SpellImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.StaxGraphWriter;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.data.AttributeListImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.viz.ColorImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.*;
import org.gephi.appearance.api.*;
import org.gephi.appearance.plugin.PartitionElementColorTransformer;
import org.gephi.appearance.plugin.palette.Palette;
import org.gephi.appearance.plugin.palette.PaletteManager;
import org.gephi.graph.api.Column;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.statistics.plugin.Modularity;
import org.openide.util.Lookup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


public class DynamicGexfGraph {

    public static String FILE_PATH = "src/main/webapp/resources/static/assets/data/";

    public static void generateGraph(int idd,UserActor act, List<UserXtrCounters> users, Map<String, List<String>> users2) {
        Gexf gexf = new GexfImpl();
        Calendar date = Calendar.getInstance();

        gexf.getMetadata()
                .setLastModified(date.getTime())
                .setCreator("Oksana Protsyk")
                .setDescription("A Social Network");


        Graph graph = gexf.getGraph();
        graph.
                setDefaultEdgeType(EdgeType.UNDIRECTED)
                .setMode(Mode.DYNAMIC)
                .setTimeType(TimeFormat.XSDDATETIME);

        AttributeList attrList = new AttributeListImpl(AttributeClass.NODE);
        graph.getAttributeLists().add(attrList);

        Attribute attphoto = attrList.createAttribute("0", AttributeType.STRING, "PHOTO_50");
        Attribute attSex = attrList.createAttribute("1", AttributeType.FLOAT, "SEX");
        Attribute attCity = attrList.createAttribute("2", AttributeType.STRING, "CITY");
        Attribute attBDATE = attrList.createAttribute("3", AttributeType.STRING, "BDATE");
        Attribute attCOUNTRY = attrList.createAttribute("4", AttributeType.BOOLEAN, "COUNTRY");
        Attribute attLISTS= attrList.createAttribute("5", AttributeType.LISTSTRING, "LISTS");
        Attribute attDOMAIN = attrList.createAttribute("6", AttributeType.STRING, "DOMAIN");
        Attribute attHOME_TOWN = attrList.createAttribute("7", AttributeType.STRING, "HOME_TOWN");
        Attribute attCONTACTS = attrList.createAttribute("8", AttributeType.LISTSTRING, "CONTACTS");
        Attribute attSITE = attrList.createAttribute("9", AttributeType.STRING, "SITE");
        Attribute attUNIVERSITIES = attrList.createAttribute("10", AttributeType.LISTSTRING, "UNIVERSITIES");
        Attribute attFOLLOWERS_COUNT= attrList.createAttribute("11", AttributeType.STRING, "FOLLOWERS_COUNT");
        Attribute attRELATIVES = attrList.createAttribute("12", AttributeType.LISTSTRING, "RELATIVES");
        Attribute attCOUNTERS = attrList.createAttribute("13", AttributeType.LISTSTRING, "COUNTERS");
        Attribute attNICKNAME = attrList.createAttribute("14", AttributeType.STRING, "NICKNAME");
        Attribute attRELATION = attrList.createAttribute("15", AttributeType.STRING, "RELATION");
        Attribute attPERSONAL = attrList.createAttribute("16", AttributeType.STRING, "PERSONAL");
        Attribute attCONNECTIONS = attrList.createAttribute("17", AttributeType.LISTSTRING, "CONNECTIONS");

        Attribute attSCHOOLS= attrList.createAttribute("18", AttributeType.LISTSTRING, "SCHOOLS");
        Attribute attOCCUPATION = attrList.createAttribute("19", AttributeType.STRING, "OCCUPATION");
        Attribute attACTIVITIES= attrList.createAttribute("20", AttributeType.LISTSTRING, "ACTIVITIES");
        Attribute attINTERESTS= attrList.createAttribute("21", AttributeType.LISTSTRING, "INTERESTS");
        Attribute attMUSIC= attrList.createAttribute("22", AttributeType.LISTSTRING, "MUSIC");
        Attribute attMOVIES= attrList.createAttribute("23", AttributeType.LISTSTRING, "MOVIES");

        Attribute attTV= attrList.createAttribute("24", AttributeType.LISTSTRING, "TV");
        Attribute attBOOKS= attrList.createAttribute("25", AttributeType.LISTSTRING, "BOOKS");
        Attribute attGAMES= attrList.createAttribute("26", AttributeType.LISTSTRING, "GAMES");
        Attribute attABOUT= attrList.createAttribute("27", AttributeType.STRING, "ABOUT");
        Attribute attQUOTES= attrList.createAttribute("28", AttributeType.LISTSTRING, "QUOTES");
        Attribute attCAREER= attrList.createAttribute("29", AttributeType.STRING, "CAREER");
        Attribute attMILITARY= attrList.createAttribute("30", AttributeType.STRING, "MILITARY");


        for (UserXtrCounters u : users) {
            Node gephi = graph.createNode(u.getId().toString());
            gephi
                    .setLabel(((u.getFirstName() == null)?"":u.getFirstName())+ ((u.getLastName() == null)?"":u.getLastName()))
                    .getAttributeValues()
                    .addValue(attphoto, (u.getPhoto50() == null)?"":u.getPhoto50())
                    .addValue(attSex,(u.getSex()==null)?"":u.getSex().toString())
                    .addValue(attCity,(u.getCity() == null)?"":u.getCity().getTitle())
                    .addValue(attBDATE,(u.getBdate() == null)?"":u.getBdate())
                    .addValue(attLISTS,"")
                    .addValue(attCOUNTRY,(u.getCountry() == null)?"":u.getCountry().getTitle())
                    .addValue(attDOMAIN,(u.getDomain() == null)?"":u.getDomain())
                    .addValue(attHOME_TOWN,(u.getHomeTown() == null)?"":u.getHomeTown())
                    .addValue(attSITE,(u.getSite() == null)?"":u.getSite())
                    .addValue(attUNIVERSITIES,(u.getUniversities() == null)?"":u.getUniversities().toString())
                    .addValue(attFOLLOWERS_COUNT,(u.getFollowersCount() == null)?"":u.getFollowersCount().toString())
                    .addValue(attRELATIVES,(u.getRelatives() == null)?"":u.getRelatives().toString())
                    .addValue(attCOUNTERS,(u.getCounters() == null)?"":u.getCounters().toString())
                    .addValue(attNICKNAME,(u.getNickname() == null)?"":u.getNickname())
                    .addValue(attRELATION,(u.getRelation() == null)?"":u.getRelation().toString())
                    .addValue(attPERSONAL,(u.getPersonal() == null)?"":u.getPersonal().toString())
                    .addValue(attCONNECTIONS,"")
                    .addValue(attSCHOOLS,(u.getSchools() == null)?"":u.getSchools().toString())
                    .addValue(attOCCUPATION,(u.getOccupation() == null)?"":u.getOccupation().getName())
                    .addValue(attACTIVITIES,(u.getActivities() == null)?"":u.getActivities())

                    .addValue(attINTERESTS, (u.getInterests() == null)?"":u.getInterests())
                    .addValue(attMUSIC,(u.getMusic() == null)?"":u.getMusic())
                    .addValue(attMOVIES,(u.getMovies() == null)?"":u.getMovies())
                    .addValue(attTV,(u.getTv() == null)?"":u.getTv())
                    .addValue(attBOOKS,(u.getBooks() == null)?"":u.getBooks())
                    .addValue(attGAMES,(u.getGames() == null)?"":u.getGames())
                    .addValue(attABOUT,(u.getAbout() == null)?"": u.getAbout())
                    .addValue(attQUOTES,(u.getQuotes() == null)?"":u.getQuotes())
                    .addValue(attCAREER,(u.getCareer() == null)?"": u.getCareer().toString())
                    .addValue(attMILITARY,(u.getMilitary() == null)?"":u.getMilitary().toString());
            System.out.println(gephi);
        }


        for (Node fr : graph.getNodes()) {
            List<String> conContacts = users2.get(fr.getId());
            if (conContacts != null) {
                for (String au : conContacts) {
                    System.out.println("Spring: " + au);
                    Node n = getNodeByID(graph, au);
                    if ((n != null) && (!fr.equals(n))) {
                        n.connectTo(fr);
                    }

                }
            }

        }

     /*   Node me = graph.createNode(act.getId().toString());
        me
                .setLabel("OKSANA")
                .getAttributeValues()
                .addValue(attUrl, "http://gephi.org")
                .addValue(attIndegree, "1");

        for (Node node : graph.getNodes()) {
            if (node.getId() != act.getId().toString()) {
                node.connectTo(me);
            }
        }
*/
        StaxGraphWriter graphWriter = new StaxGraphWriter();
        File f = new File(FILE_PATH+idd+".gexf");
        Writer out;
        try {
            out = new FileWriter(f, false);
            graphWriter.writeToStream(gexf, out, "UTF-8");
            System.out.println(f.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Node getNodeByID(Graph graph, String id) {
        for (Node n : graph.getNodes()) {
            if (n.getId().equals(id)) {
                return n;
            }
        }
        return null;
    }
}
